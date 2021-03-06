package TIM8.medicalcenter.controller;

import TIM8.medicalcenter.dto.*;
import TIM8.medicalcenter.dto.response.AppointmentRequestDTOResponse;
import TIM8.medicalcenter.exception.ResourceConflictException;
import TIM8.medicalcenter.model.Appointment;
import TIM8.medicalcenter.model.AppointmentRequest;
import TIM8.medicalcenter.model.Room;
import TIM8.medicalcenter.model.grading.PatientClinicGrades;
import TIM8.medicalcenter.model.grading.PatientDoctorGrades;
import TIM8.medicalcenter.model.users.*;
import TIM8.medicalcenter.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @Autowired
    PatientDoctorGradesService patientDoctorGradesService;

    @Autowired
    PatientClinicGradesService patientClinicGradesService;

    /**
     *
     * Odobravanje registracije za nekog pacijenta,moze biti uradjeno samo od strane CCA
     * @param id
     * @return
     */
    @PreAuthorize("hasRole('CCADMIN')")
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

    @PreAuthorize("hasRole('CCADMIN')")
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
    @PreAuthorize("hasRole('CCADMIN')")
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

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(consumes = "application/json",value = "/createDoctor",method = RequestMethod.POST)
    public ResponseEntity<?> createDoctor(@RequestBody CreateDoctorDTO doc) {
        personService.saveDoctor(doc);

        return new ResponseEntity<>(doc, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/averageClinicGrade",method = RequestMethod.POST)
    public ResponseEntity<?> averageClinicGrade(@RequestBody Id id) {
        List<PatientClinicGrades> grades = patientClinicGradesService.findClinicGrades(id.id);

        double response = 0.0;

        for(PatientClinicGrades grade : grades) {
            response += grade.getGrade();
        }

        response = response/grades.size();

        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/financialReport", method=RequestMethod.POST)
    public ResponseEntity<?> financialReport(@RequestBody Dates dates) {
        String dat1 = dates.beginDate;
        String dat2 = dates.endDate;

        int money = 0;

        int year = Integer.parseInt(dat1.split("-")[0]);
        int month = Integer.parseInt(dat1.split("-")[1]);
        int day = Integer.parseInt(dat1.split("-")[2]);


        List<Appointment> appointments = appointmentService.findAdminAppointments(dates.id);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        Date d1 = cal.getTime();


        year = Integer.parseInt(dat2.split("-")[0]);
        month = Integer.parseInt(dat2.split("-")[1]);
        day = Integer.parseInt(dat2.split("-")[2]);

        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        Date d2 = cal.getTime();

        for(Appointment a : appointments) {
            if(d1.before(a.getDate()) && a.getDate().before(d2))
                money += a.getPrice()*(100-a.getDiscount())/100;
        }

        return new ResponseEntity<>(money, HttpStatus.OK);
    }

    static class Dates {
        public String beginDate;
        public String endDate;
        public Long id;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/getAdminDoctorsGrades",method = RequestMethod.POST)
    public ResponseEntity<?> getAdminDoctorsGrades(@RequestBody Id id) {
        List<Doctor> doctors = personService.findAdminsDoctors(id.id);
        List<Doctor> active = new ArrayList<>();
        for(Doctor d : doctors){
            if(!d.getStatus().equals("DELETED"))
                active.add(d);
        }

        List<DoctorGradeDTO> response = new ArrayList<>();

        for(Doctor d : active) {
            List<PatientDoctorGrades> grades = patientDoctorGradesService.findDoctorGrades(d.getId());
            double average = 0.0;
            for(PatientDoctorGrades pdg : grades) {
                average += pdg.getGrade();
            }
            average = average/grades.size();
            response.add(new DoctorGradeDTO(d.getFirstName(), d.getLastName(), average));
        }

        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/getAdminDoctors",method = RequestMethod.POST)
    public ResponseEntity<?> getAdminDoctors(@RequestBody Id id) {
        List<Doctor> doctors = personService.findAdminsDoctors(id.id);
        List<DoctorDTO> response = new ArrayList<>();
        for(Doctor d : doctors){
            if(!d.getStatus().equals("DELETED"))
                response.add(new DoctorDTO(d.getFirstName(),d.getLastName(),d.getId()));
        }
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/deleteDoctor",method = RequestMethod.POST)
    public ResponseEntity<?> deleteDoctor(@RequestBody Id id) {
        List<Appointment> appointments = appointmentService.findAll();
        Calendar cal = Calendar.getInstance();
        Date d = cal.getTime();

        int deletable = 1;

        for(Appointment app : appointments){
            if(app.getDoctor().getId() == id.id && d.before(app.getDate())){
                deletable=0;
                break;
            }
        }
        int a=0;
        if(deletable==1) {
            a = personService.updatePersonStatus("DELETED", id.id);

            return new ResponseEntity<>(a, HttpStatus.OK);
        }

        return new ResponseEntity<>(0, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/deleteRoom",method = RequestMethod.POST)
    public ResponseEntity<?> deleteRoom(@RequestBody Id id) {
        List<Appointment> appointments = appointmentService.findAll();
        Calendar cal = Calendar.getInstance();
        Date d = cal.getTime();

        int deletable = 1;

        for(Appointment app : appointments){
            if(app.getRoom().getId() == id.id && d.before(app.getDate())){
                deletable=0;
                break;
            }
        }

        if(deletable==1) {
            for(Appointment app : appointments){
                if(app.getRoom().getId() == id.id)
                    appointmentService.updateAppointmentRoom(id.id);
            }
            roomService.deleteRoom(id.id);

            return new ResponseEntity<>(1, HttpStatus.OK);
        }

        return new ResponseEntity<>(0, HttpStatus.OK);
    }

    static class Id {
        public Long id;
    }

    /**
     * Funkcija za registraovanje CCA
     * @param administratorDTO
     * @return
     */
    @PreAuthorize("hasRole('CCADMIN')")
    @RequestMapping(consumes = "application/json",value = "/registerClinicCentreAdmin",method = RequestMethod.POST)
    public ResponseEntity<?> registerClinicCentreAdmin(@RequestBody AdministratorDTO administratorDTO){
        Person person= personService.findOneByUsername(administratorDTO.getUsername());
        if(person != null){
            throw new ResourceConflictException(administratorDTO.getId(), "Username already exists");
        }
        ClinicsAdministrator person1 = (ClinicsAdministrator) personService.saveClinicCentreAdministrator(administratorDTO,"PENDING","ROLE_CCADMIN");
        return new ResponseEntity<>(new AdministratorDTO(person1), HttpStatus.CREATED);

    }

    /**
     *
     * Funckija kojom administrator pravi novi pregled,birajuci neki od pregleda iz liste zahteva za pregled
     * @param request
     * @return
     */
    @PreAuthorize("hasRole('ADMIN')")
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
    /**
     *
     * Funkcija koja adminu vraca sve zahteve za pregled ili operaciju koje je pre toga napravio doktor
     * @return
     */
    @PreAuthorize("hasRole('ADMIN')")
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



