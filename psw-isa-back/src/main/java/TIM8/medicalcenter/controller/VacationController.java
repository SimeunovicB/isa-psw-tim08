package TIM8.medicalcenter.controller;


import TIM8.medicalcenter.dto.VacationRequestsDTO;
import TIM8.medicalcenter.model.Vacation;
import TIM8.medicalcenter.model.users.Administrator;
import TIM8.medicalcenter.model.users.Doctor;
import TIM8.medicalcenter.model.users.Nurse;
import TIM8.medicalcenter.model.users.Person;
import TIM8.medicalcenter.service.EmailService;
import TIM8.medicalcenter.service.PersonService;
import TIM8.medicalcenter.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "api/vacation",produces = MediaType.APPLICATION_JSON_VALUE)
public class VacationController {

    @Autowired
    private VacationService vacationService;

    @Autowired
    private PersonService personService;

    @Autowired
    private EmailService emailService;

    /**
     * Funkcija kojom administrator preuzima sve zahteve za godisnje odmore unitar njegove klinike
     * @return
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value="/getPending")
    public ResponseEntity<?> getAllClinics() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();
        Administrator p = (Administrator) personService.findOneByUsername(username);

        List<Vacation> pending = vacationService.findPending();
        List<VacationRequestsDTO> requests = new ArrayList<>();

        for(Vacation v : pending){
            if(v.getStaff() instanceof Doctor){
                if(p.getClinic().getId() == ((Doctor) v.getStaff()).getClinic().getId()){
                    requests.add(new VacationRequestsDTO(v));
                }
            }
            else{
                if(p.getClinic().getId() == ((Nurse)v.getStaff()).getClinic().getId()){
                    requests.add(new VacationRequestsDTO(v));
                }
            }

        }
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    /**
     * Odobravanje zahteva za godisnjim odmorom
     * @param id
     * @return
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = "application/json", value = "/approve")
    public ResponseEntity<?> approveRequest(@RequestBody Id id) {
        Vacation v = vacationService.findOneById(id.id);
        if(v.getId() != null) {
            vacationService.updateVacationStatus("APPROVED", id.id);
            v = vacationService.findOneById(id.id);
            return new ResponseEntity<>(new VacationRequestsDTO(v), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(new VacationRequestsDTO(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Odbijanje zahteva za godisnjim odmorom
     * @param id
     * @return
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = "application/json", value = "/decline")
    public ResponseEntity<?> declineRequest(@RequestBody Odbijanje id) {
        Vacation v = vacationService.findOneById(id.id);
        if(v.getId() != null) {
            vacationService.updateVacationStatus("DECLINED", id.id);
            v = vacationService.findOneById(id.id);
            String razlog = id.razlog;
            try {
                emailService.declineVacationRequest(v.getStaff(), razlog);
            }catch(Exception e){
                e.printStackTrace();
            }
            return new ResponseEntity<>(new VacationRequestsDTO(v), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(new VacationRequestsDTO(), HttpStatus.BAD_REQUEST);
    }

    static class Odbijanje {
        public Long id;
        public String razlog;
    }

    @PreAuthorize("hasRole('MEDICAL_STAFF')")
    @PostMapping(consumes = "application/json", value = "/create")
    public ResponseEntity<?> createRequest(@RequestBody VacationRequest vr) {
        String dat = vr.startDate;
        int year = Integer.parseInt(dat.split("-")[0]);
        int month = Integer.parseInt(dat.split("-")[1]);
        int day = Integer.parseInt(dat.split("-")[2]);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        Date d1 = cal.getTime();

        String dat2 = vr.endDate;
        int year2 = Integer.parseInt(dat2.split("-")[0]);
        int month2 = Integer.parseInt(dat2.split("-")[1]);
        int day2 = Integer.parseInt(dat2.split("-")[2]);
        cal.set(Calendar.YEAR, year2);
        cal.set(Calendar.MONTH, month2-1);
        cal.set(Calendar.DAY_OF_MONTH, day2);
        Date d2 = cal.getTime();
        Vacation v = new Vacation(d1, d2, "PENDING", personService.findOneById(vr.id));
        vacationService.save(v);
        return new ResponseEntity<>(new VacationRequestsDTO(v), HttpStatus.ACCEPTED);
    }

    static class VacationRequest {
        public String startDate;
        public String endDate;
        public Long id;
    }

    static class Id {
        public Long id;
    }
}
