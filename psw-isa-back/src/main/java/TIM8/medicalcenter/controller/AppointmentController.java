package TIM8.medicalcenter.controller;

import TIM8.medicalcenter.dto.*;
import TIM8.medicalcenter.dto.Request.PredefAppointmentDTORequest;
import TIM8.medicalcenter.model.Appointment;
import TIM8.medicalcenter.model.AppointmentRequest;
import TIM8.medicalcenter.model.Clinic;
import TIM8.medicalcenter.model.Room;
import TIM8.medicalcenter.model.grading.PatientClinicGrades;
import TIM8.medicalcenter.model.grading.PatientDoctorGrades;
import TIM8.medicalcenter.model.users.Doctor;
import TIM8.medicalcenter.model.users.Patient;
import TIM8.medicalcenter.model.users.Person;
import TIM8.medicalcenter.repository.PatientClinicGradesRepository;
import TIM8.medicalcenter.repository.PatientDoctorGradesRepository;
import TIM8.medicalcenter.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(value = "api/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private ClinicService clinicService;
    @Autowired
    private PersonService personService;
    @Autowired
    private PatientDoctorGradesRepository gradesDoctor;
    @Autowired
    private PatientClinicGradesRepository gradesClinic;

    @Autowired
    private AppointmentRequestService appointmentRequestService;
    @Autowired
    private RoomService roomService;

    /**
     * Fukcija kojom pacijent vrsi pretragu svih klinika,da bi mogao da zakaze termin za pregled
     * @param date
     * @param type
     * @return
     * @throws ParseException
     */
    @PreAuthorize("hasRole('PATIENT')")
    @GetMapping(value="/findClinic")
    public ResponseEntity<?> findClinics(@RequestParam String date, @RequestParam String type) throws ParseException {
        List<ClinicDTO> clinics = new ArrayList<>();

        List<PatientClinicGrades> ocene = gradesClinic.findAll();
        if(date.equals("")&&type.equals("")){
            List<Clinic> clinic = clinicService.findAll();
            for (Clinic c:clinic) {
                double sum =0;
                for(PatientClinicGrades p : ocene)
                    if(c.getId()==p.getClinic().getId())
                        sum+=p.getGrade();
                System.out.println(sum+" "+ocene.size());
                ClinicDTO clinic1 = new ClinicDTO(c);
                clinic1.setAvg(sum/ocene.size());
                clinics.add(clinic1);
            }
            return new ResponseEntity<>(clinics, HttpStatus.OK);
        }
        SimpleDateFormat formatter6=new SimpleDateFormat("yyyy-MM-dd");
        Date date1=new Date();
        try {
            date1 = formatter6.parse(date);
        }
        catch(Exception e) {
            return null;
        }
        List<Appointment> appointments = appointmentService.findAppointments(type);
        List<AppointmentDTO> apps = new ArrayList<>();

        for (Appointment a:appointments) {
            String temp =a.getDate().toString().substring(0,10);
            Date date2=new Date();
            date2 = formatter6.parse(temp);
            if (date2.equals(date1) && a.getPatient()==null)
                apps.add(new AppointmentDTO(a.getDoctor(),a.getDate(),a.getType()));
        }
        List<String> names = new ArrayList<>();
        for (AppointmentDTO a:apps) {
            System.out.println(a.getDoctor().getClinic().getName());
            if(names.contains(a.getDoctor().getClinic().getName()))
                continue;
            double sum =0;
            for(PatientClinicGrades p : ocene)
                if(a.getDoctor().getClinic().getId()==p.getClinic().getId())
                     sum+=p.getGrade();
            System.out.println(sum+" "+ocene.size());
            ClinicDTO clinic = new ClinicDTO(a.getDoctor().getClinic());
            clinic.setAvg(sum/ocene.size());

            names.add(a.getDoctor().getClinic().getName());
            clinics.add(clinic);
        }
        return new ResponseEntity<>(clinics, HttpStatus.OK);
    }

    @RequestMapping(consumes = "application/json",value="/getAppointments",method = RequestMethod.GET)
    public ResponseEntity<?> getAppointments(){
        List<Appointment> apps = appointmentService.findAll();
        List<AppointmentDTO> appDto = new ArrayList<>();
        for (Appointment a:apps) {
            if(!a.getStatus().equals(""))
                continue;
            AppointmentDTO ap = new AppointmentDTO(a);
            ap.setDoctor(null);
            appDto.add(ap);
        }
        //TODO: Za koga je ovo i sta radi
        return new ResponseEntity<>(appDto, HttpStatus.OK);
    }

    /**
     * Pretraga doktora unitar odabrane klinike,koji imaju slobodan termin za odredjeni dan
     * @param clinicName
     * @param date
     * @param type
     * @return
     * @throws ParseException
     */
    @PreAuthorize("hasRole('PATIENT')")
    @RequestMapping(value="/findClinic/doctors",method = RequestMethod.GET)
    public ResponseEntity<?> findDoctors(@RequestParam String clinicName,@RequestParam String date, @RequestParam String type) throws ParseException {
        SimpleDateFormat formatter6=new SimpleDateFormat("yyyy-MM-dd");
        Date date1=new Date();
        try {
            date1 = formatter6.parse(date);
        }
        catch(Exception e) {
            new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
        List<Appointment> appointments = appointmentService.findAppointments(type);
        System.out.println(appointments.size());
        List<AppointmentDTO> apps = new ArrayList<>();
        List<PersonDTO> doctors = new ArrayList<>();
        for (Appointment a:appointments) {
            String temp =a.getDate().toString().substring(0,10);
            Date date2=new Date();
            date2 = formatter6.parse(temp);
            if (date2.equals(date1) && a.getPatient()==null)
                apps.add(new AppointmentDTO(a.getDoctor(),a.getDate(),a.getType()));
        }
        System.out.println(apps.size());
        List<String> names = new ArrayList<>();
        for (AppointmentDTO a:apps) {
            System.out.println(a.getDoctor().getClinic().getName());
            if(names.contains(a.getDoctor().getFirstName() ) || !a.getDoctor().getClinic().getName().equals(clinicName))
                continue;
            names.add(a.getDoctor().getFirstName());
            doctors.add(new PersonDTO(a.getDoctor()));
        }

        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    /**
     * Fukcija kojom pacijent rezervise prvi slobodan termin za pregled odredjenog dana u odredjeno vreme
     * @param request
     * @return
     */
    @PreAuthorize("hasRole('PATIENT')")
    @RequestMapping(consumes = "application/json",value="/makeApp",method = RequestMethod.POST)
    public ResponseEntity<?> makeApp(@RequestBody Req request) {
        List<ClinicDTO> clinics = new ArrayList<>();
        if(request.date.equals("")&&request.type.equals("")){
            return null;
        }
        SimpleDateFormat formatter6=new SimpleDateFormat("yyyy-MM-dd");
        Date date1=new Date();
        try {
            date1 = formatter6.parse(request.date);
        }
        catch(Exception e) {
            return null;
        }
        List<Appointment> appointments = appointmentService.findAppointments(request.type);
        List<String> termins = new ArrayList<>();
        for (Appointment a:appointments) {
            String temp =a.getDate().toString().substring(0,10);
            Date date2=new Date();
            try {
                date2 = formatter6.parse(temp);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println(date2.toString()+" "+date1.toString()+" "+a.getDoctor().getId()+" "+request.id);

            if (date2.equals(date1) && a.getDoctor().getId()==request.id && a.getType().equals(request.type)
                    && a.getPatient()==null)

                termins.add(a.getDate().toString());

        }
        Collections.sort(termins);
        if(termins.size()==0)
            return  null;
        String min = termins.get(0);
        Long id = 0L;
        for (Appointment a:appointments) {
            String temp =a.getDate().toString().substring(0,10);
            Date date2=new Date();
            try {
                date2 = formatter6.parse(temp);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println(date2.toString()+" "+date1.toString()+" "+a.getDoctor().getId()+" "+request.id);
            if (date2.equals(date1) && a.getDoctor().getId()==request.id && a.getType().equals(request.type)
                    && a.getPatient()==null && a.getDate().toString().equals(min) )
                        id = a.getId();

        }
        System.out.println(id+" "+request.patientId);
        try {
            appointmentService.reserve(new PredefAppointmentDTORequest(request.patientId, id));
        }catch (Exception e){
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    static class Req{
        public int id;
        public Long patientId;
        public String type;
        public String date;

    }
    static class grade{
        public Long doctorId;
        public Long patientId;
        public double value;
    }

    /**
     * Funkcija kojom pacijent ocenjuje doktora,ukoliko ga je vec ocenio prosla ocena se brise i upisuje se nova
     * @param request
     * @return
     */
    @PreAuthorize("hasRole('PATIENT')")
    @RequestMapping(consumes = "application/json",value="/makeGradeDoctor",method = RequestMethod.POST)
    public ResponseEntity<?> makeGradeDoctor(@RequestBody grade request) {
        PatientDoctorGrades a = new PatientDoctorGrades();
        a.setPatient((Patient)personService.findOneById(request.patientId));
        a.setDoctor((Doctor) personService.findOneById(request.doctorId));
        a.setGrade(request.value);
        gradesDoctor.save(a);
        return new ResponseEntity<>( HttpStatus.OK);

    }
    static class clinicGrade{
        public String clinicName;
        public Long patientId;
        public double value;
    }

    /**
     * Funkcija kojom pacijent ocenjuje kliniku,ukoliko je vec ocenio prosla ocena se brise i upisuje se nova
     * @param request
     * @return
     */
    @PreAuthorize("hasRole('PATIENT')")
    @RequestMapping(consumes = "application/json",value="/makeGradeClinic",method = RequestMethod.POST)
    public ResponseEntity<?> makeGradeClinic(@RequestBody clinicGrade request) {
        PatientClinicGrades a = new PatientClinicGrades();
        a.setPatient((Patient)personService.findOneById(request.patientId));
        a.setClinic((Clinic) clinicService.findOneByName(request.clinicName));
        a.setGrade(request.value);
        System.out.println(a.getGrade()+" "+a.getPatient().getId()+" "+a.getClinic().getId());
        gradesClinic.save(a);
        return new ResponseEntity<>( HttpStatus.OK);

    }
    @RequestMapping(consumes = "application/json",value="/doctorGrade",method = RequestMethod.POST)
    public ResponseEntity<?> doctorGrade(@RequestBody Req request) {
        List<Appointment> apps = appointmentService.findAll();
        List<DoctorDTO> appDto = new ArrayList<>();
        List<PatientDoctorGrades> ocene = gradesDoctor.findAll();
        List<String> names = new ArrayList<>();
        for (Appointment a:apps) {
            if(a.getPatient()==null)
                continue;
            if(a.getPatient().getId()!=request.patientId )
                continue;
            AppointmentDTO ap = new AppointmentDTO(a);
            if(names.contains(ap.getDoctor().getFirstName()))
                continue;
            Boolean ocenjen=false;
            for(PatientDoctorGrades p : ocene){
                System.out.println(p.getDoctor().getId()+" "+a.getDoctor().getId() +""+p.getPatient().getId()+""+ request.patientId);
                if(p.getDoctor().getId()== a.getDoctor().getId() && p.getPatient().getId()== request.patientId)
                    ocenjen=true;
            }
            if(!ocenjen) {
                names.add(ap.getDoctor().getFirstName());
                appDto.add(new DoctorDTO(ap.getDoctor().getFirstName(), ap.getDoctor().getLastName(), ap.getDoctor().getId(), ap.getDoctor().getClinic().getId()));
            }
        }
        return new ResponseEntity<>(appDto, HttpStatus.OK);

    }
    @RequestMapping(consumes = "application/json",value="/clinicGrade",method = RequestMethod.POST)
    public ResponseEntity<?> clinicGrade(@RequestBody Req request) {
        List<Appointment> apps = appointmentService.findAll();
        List<ClinicDTO> appDto = new ArrayList<>();
        List<String> names = new ArrayList<>();
        List<PatientClinicGrades> ocene = gradesClinic.findAll();
        for (Appointment a:apps) {
            if(a.getPatient()==null)
                continue;
            if(a.getPatient().getId()!=request.patientId)
                continue;
            AppointmentDTO ap = new AppointmentDTO(a);
            if(names.contains(ap.getDoctor().getClinic().getName()))
                continue;
            Boolean ocenjen=false;
            for(PatientClinicGrades p : ocene){
                System.out.println(p.getClinic().getId()+" "+a.getDoctor().getClinic().getId() +""+p.getPatient().getId()+""+ request.patientId);
                if(p.getClinic().getId()== a.getDoctor().getClinic().getId() && p.getPatient().getId()== request.patientId)
                    ocenjen=true;
            }
            if(!ocenjen) {
                names.add(ap.getDoctor().getClinic().getName());
                appDto.add(new ClinicDTO(ap.getDoctor().getClinic().getId(), ap.getDoctor().getClinic().getName()));
            }
        }

        return new ResponseEntity<>(appDto, HttpStatus.OK);

    }

    /**
     * Funkcija koja vraca sve preglede na kojima je doktor,i za koje ima upisan pacijent,koristi se za kalendar,valjda.
     * @param doctorId
     * @return
     */
    @PreAuthorize("hasRole('MEDICAL_STAFF')")
    @RequestMapping(value="/getAppointmentsForDoctor",method = RequestMethod.GET)
    public ResponseEntity<?> getAppointmentsForDoctor(@RequestParam String doctorId){
        Long id = Long.parseLong(doctorId.substring(1));
        List<Appointment> apps = appointmentService.findAllByDoctorId(id);
        List<AppointmentDTO> appDto = new ArrayList<>();
        for (Appointment a:apps) {
            if(!a.getStatus().equals("") || a.getPatient() == null)
                continue;

            AppointmentDTO ap = new AppointmentDTO(a);

            appDto.add(ap);
        }
        return new ResponseEntity<>(appDto, HttpStatus.OK);
    }
    /**
     * funkcija koja preuzima sve predefinisane preglede,odnosno preglede na kojima nema pacijenta,koje pacijent moze da zakaze
     */
    @PreAuthorize("hasRole('PATIENT')")
    @RequestMapping(value="/getIncomingAppointmnents",method = RequestMethod.GET)
    public ResponseEntity<?> getPredefAppointments() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();
        Person p = personService.findOneByUsername(username);
        List<AppointmentDTO> response = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        Date d = cal.getTime();
        String str = d.toString().substring(0,10);
        if (p instanceof Patient) {
            List<Appointment> apps = appointmentService.findAll();

            for (Appointment a : apps) {
                if (a.getPatient() != null) {

                    if (a.getPatient().getId() == p.getId()) {
                        response.add(new AppointmentDTO(a.getId(), a.getDoctor(), a.getDate(), a.getType(), null, a.getPrice(), a.getDiscount(), a.getRoom().getName()));
                    }
                }

            }
        } else {
            List<Appointment> apps = appointmentService.findAll();

            for (Appointment a : apps) {
                if (a.getPatient() != null) {

                    if (a.getDoctor().getId() == p.getId() && a.getPatient() != null) {
                        response.add(new AppointmentDTO(a.getId(), a.getDoctor(), a.getDate(), a.getType(), null, a.getPrice(), a.getDiscount(), a.getRoom().getName()));
                    }
                }

            }


        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * funkcija kojom doktor ili pacijent mogu da otkazu pregled,u slucaju pacijenta pacijent se brise iz pregleda i on
     * postaje dostupan u slucaju da doktor brise prelged se brise iz baze
     * @param request
     * @return
     */
    @PreAuthorize("hasAnyRole('MEDICAL_STAFF','PATIENT')")
    @RequestMapping(value = "/cancleAppointment",consumes = "application/json",method = RequestMethod.POST)
    public ResponseEntity<?> cancleAppointment(@RequestBody MedicalExaminationId request) {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();
        Person p = personService.findOneByUsername(username);
        appointmentService.cancle(p,request.id);

        return new ResponseEntity<>(null,HttpStatus.ACCEPTED);


    }
    static class MedicalExaminationId {
        public Long id;
    }


    /**
     * Funkcija koja doktoru ili pacijentu vraca preglede koji se jos nisu odrzali
     * @return
     */
    @PreAuthorize("hasAnyRole('MEDICAL_STAFF','PATIENT')")
    @RequestMapping(value="/getPredefAppointment",method = RequestMethod.GET)
    public ResponseEntity<?> getIncomingAppointmeents() {
        List<Appointment> apps =  appointmentService.findAll();
        List<AppointmentDTO> response = new ArrayList<>();
        for(Appointment a : apps){
            if(a.getPatient() == null){
                response.add(new AppointmentDTO(a.getId(),a.getDoctor(),a.getDate(),a.getType(),null,a.getPrice(),a.getDiscount(),a.getRoom().getName()));
            }
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    /**
     * Funkcija kojom pacijent rezervise slobodan predefinisan pregled,ova funkcija je u servisu zasticena transakcijom
     * @param request
     * @return
     */
    @PreAuthorize("hasRole('PATIENT')")
    @PostMapping(consumes = "application/json",value = "/reservePredef")
    public ResponseEntity<?> reservePredefApp(@RequestBody PredefAppointmentDTORequest request){
        appointmentService.reserve(request);
        return new ResponseEntity<>(request,HttpStatus.ACCEPTED);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,value = "/createPredef",method = RequestMethod.POST)
    public ResponseEntity<?> createPredef(@RequestBody CreatePredefDTO req){
        appointmentService.createPredef(req);
        return new ResponseEntity<>(null,HttpStatus.OK);
    }
    //@Scheduled(cron="0 0 0 1/1 * ? *")
    //@Scheduled(fixedRate = 1000)
    public void generateAppointments() {
        List<AppointmentRequest> requests = appointmentRequestService.findAll();
        List<Room> rooms = roomService.findAll();
        List<Appointment> appointments = appointmentService.findAll();

        for(AppointmentRequest ar : requests) {
            int flag = 0;
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
            System.out.println("prolazak");




            for(Room r : rooms) {
                HashSet<Integer> free_hours = new HashSet<>();
                for(int i=8;i<=17;i++){
                    free_hours.add(i);
                }
                Calendar cal = Calendar.getInstance();
                for(Appointment appointment : r.getAppointments()) {
                    int calYears = date.getYear();
                    int calMonths = date.getMonth();
                    int calDays = date.getDay();

                    int appYears = appointment.getDate().getYear();
                    int appMonths = appointment.getDate().getMonth();
                    int appDays = appointment.getDate().getDay();
                    if(cal.getTime().getYear() == appointment.getDate().getYear() && cal.getTime().getMonth() == appointment.getDate().getMonth() && cal.getTime().getYear() == appointment.getDate().getDay()){

                        free_hours.remove(appointment.getDate().getHours());
                    }
                }
                for(int i=8;i<17;i++){
                    if(free_hours.contains(i)){
                        a.setRoom(r);
                        Calendar cal2 = Calendar.getInstance();
                        cal.set(date.getYear(),date.getMonth(),date.getDay(),i,0,0);
                        flag = 1;
                        Appointment a1 = appointmentService.save(a);

                        appointmentRequestService.delete(ar.getId());
                        break;

                    }
                }
                if(flag == 1){
                    break;
                }
            }


        }
    }


}
