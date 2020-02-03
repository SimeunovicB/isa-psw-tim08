package TIM8.medicalcenter.controller;

import TIM8.medicalcenter.dto.AdministratorDTO;
import TIM8.medicalcenter.dto.MakeAppointmentDTORequest;
import TIM8.medicalcenter.dto.PersonDTO;
import TIM8.medicalcenter.dto.response.AppointmentRequestDTOResponse;
import TIM8.medicalcenter.exception.ResourceConflictException;
import TIM8.medicalcenter.model.Appointment;
import TIM8.medicalcenter.model.AppointmentRequest;
import TIM8.medicalcenter.model.Room;
import TIM8.medicalcenter.model.users.*;
import TIM8.medicalcenter.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping(value = "api/administrator")
public class AdministratorController {

    @Autowired
    PersonService personService;

    @Autowired
    EmailService emailService;

    @Autowired
    RoomService roomService;

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    AppointmentRequestService appointmentRequestService;

    /**
     *
     * Odobravanje registracije za nekog pacijenta,moze biti uradjeno samo od strane CCA
     * @param id
     * @return
     */
    @PostMapping(consumes = "application/json",value ="/approveRegistration/{id}")
    public ResponseEntity<PersonDTO> updateStatusApproved(@PathVariable Long id) {

        Person person = personService.findOneById(id);
        if(person != null && person.getDecriminatorValue().equals("P") && person.getStatus().equalsIgnoreCase("PENDING")){
            personService.updatePersonStatus("ACCEPTED",person.getId());
            try {
                emailService.userAccepted(person);
            }catch( Exception e ){

            }
            return new ResponseEntity<>(new PersonDTO(person),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new PersonDTO(),HttpStatus.NO_CONTENT);
        }
    }


    /**
     *
     * Odbijanje registracije nekog pacijenta moze biti uradjeno samo od strane CCA
     * @param id
     * @return
     */
    @PostMapping(consumes = "application/json",value = "/rejectRegistration/{id}")
    public ResponseEntity<PersonDTO> updateStatusRejected(@PathVariable Long id){
        Person person = personService.findOneById(id);
        if(person != null && person.getDecriminatorValue().equals("P") && person.getStatus().equalsIgnoreCase("PENDING")){
            personService.updatePersonStatus("REJECTED",person.getId());
            try {
                emailService.userDenied(person);
            }catch( Exception e ){

            }
            return new ResponseEntity<>(new PersonDTO(person),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new PersonDTO(),HttpStatus.NO_CONTENT);
        }

    }

    /**
     *
     * Funkcija za registrovanje Administratora klinike
     * @param administratorDTO
     * @return
     */
    @RequestMapping(consumes = "application/json",value = "/registerAdmin",method = RequestMethod.POST)
    public ResponseEntity<?> registerAdmin(@RequestBody AdministratorDTO administratorDTO){
        Administrator administrator1 = (Administrator) personService.findOneByUsername(administratorDTO.getUsername());
        if(administrator1 != null){
            throw new ResourceConflictException(administratorDTO.getId(), "Username already exists");
        }
        Administrator person1 = (Administrator) personService.saveAdministrator(administratorDTO,"PENDING","ROLE_ADMIN");
        //HttpHeaders headers = new HttpHeaders();
        //headers.setLocation(ucBuilder.path("/api/user/{userId}").buildAndExpand(person1.getId()).toUri());
        return new ResponseEntity<>(new AdministratorDTO(person1), HttpStatus.CREATED);

    }

    /**
     * Funkcija za registraovanje CCA
     * @param administratorDTO
     * @return
     */
    @RequestMapping(consumes = "application/json",value = "/registerClinicCentreAdmin",method = RequestMethod.POST)
    public ResponseEntity<?> registerClinicCentreAdmin(@RequestBody AdministratorDTO administratorDTO){
        Person person= personService.findOneByUsername(administratorDTO.getUsername());
        if(person != null){
            throw new ResourceConflictException(administratorDTO.getId(), "Username already exists");
        }
        ClinicsAdministrator person1 = (ClinicsAdministrator) personService.saveClinicCentreAdministrator(administratorDTO,"PENDING","ROLE_ADMIN");
        return new ResponseEntity<>(new AdministratorDTO(person1), HttpStatus.CREATED);

    }

    /**
     *
     * Funckija kojom administrator pravi novi pregled,birajuci neki od pregleda iz liste zahteva za pregled
     * @param request
     * @return
     */
    @RequestMapping(consumes = "application/json",value ="/makeAppointment",method = RequestMethod.POST)
    public ResponseEntity<?> makeAppoitment(@RequestBody MakeAppointmentDTORequest request){
        Appointment a = new Appointment();
        Doctor d = (Doctor) personService.findOneById(request.getDoctor());
        Patient p = (Patient) personService.findOneById(request.getPatient());
        Room r = roomService.findOneById(request.getRoom());
        appointmentRequestService.delete(request.getAppointmentRequestId());
        a.setPatient(p);
        a.setDoctor(d);
        a.setRoom(r);
        a.setStatus("ACTIVE");
        a.setType(request.getType());
        a.setDate(request.getDate());
        a.setDiscount(10);
        a.setPrice(10000);
        Appointment a1 = appointmentService.save(a);
        return new ResponseEntity<>(null,HttpStatus.OK);
    }


    //@Scheduled(cron="0 0 0 1/1 * ? *")
    //@Scheduled(fixedRate = 1000)
    public void methodC() {
        List<AppointmentRequest> requests = appointmentRequestService.findAll();
        List<Room> rooms = roomService.findAll();
        List<Appointment> appointments = appointmentService.findAll();

        for(AppointmentRequest ar : requests) {
            Date date = Calendar.getInstance().getTime();

            Appointment a = new Appointment();
            Doctor d = (Doctor) personService.findOneById(ar.getDoctor_id());
            Patient p = (Patient) personService.findOneById(ar.getPatient_id());
            a.setPatient(p);
            a.setDoctor(d);
            a.setStatus("ACTIVE");
            a.setType(ar.getAppointment_type());
            a.setDiscount(10);
            a.setPrice(10000);

            List<Integer> free_hours = new ArrayList<>();
            for(int i=8;i<=17;i++){
                free_hours.add(i);
            }

            for(Room r : rooms) {
                for(Appointment appointment : r.getAppointments()) {
                    Calendar cal = Calendar.getInstance();
                    cal.set(date.getYear(),date.getMonth(),date.getDay(),0,0,0);

                    if(cal.getTime().getYear() == appointment.getDate().getYear() &&
                            cal.getTime().getMonth() == appointment.getDate().getMonth() &&
                            cal.getTime().getYear() == appointment.getDate().getDay())
                    {
                        /*for(int i=0;i<free_hours.size();i++){
                            if(appointment.getDate().getHours() == free_hours.toArray()[i]){

                            }
                        }*/
                    }

                    a.setRoom(r);

                    //a.setDate(d1);
                    }
            }
            Appointment a1 = appointmentService.save(a);

            appointmentRequestService.delete(ar.getId());

        }
    }

    /**
     *
     * Funkcija koja adminu vraca sve zahteve za pregled ili operaciju koje je pre toga napravio doktor
     * @return
     */
    @RequestMapping(value = "/getAppointmentRequests",method = RequestMethod.GET)
    public ResponseEntity<?> getAppointmentRequests(){
        List<AppointmentRequest> requests = appointmentRequestService.findAll();
        List<AppointmentRequestDTOResponse> responses = new ArrayList<>();
        for(AppointmentRequest a : requests){
            responses.add(new AppointmentRequestDTOResponse(a.getId(),a.getDoctor_id(),a.getPatient_id(),a.getDate(),a.getAppointment_type()));
        }

        return new ResponseEntity<>(responses,HttpStatus.OK);
    }





}



