package TIM8.medicalcenter.controller;

import TIM8.medicalcenter.dto.AppointmentDTO;
import TIM8.medicalcenter.dto.ClinicDTO;
import TIM8.medicalcenter.dto.CreatePredefDTO;
import TIM8.medicalcenter.dto.PersonDTO;
import TIM8.medicalcenter.dto.Request.PredefAppointmentDTORequest;
import TIM8.medicalcenter.model.Appointment;
import TIM8.medicalcenter.model.Clinic;
import TIM8.medicalcenter.service.AppointmentService;
import TIM8.medicalcenter.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "api/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private ClinicService clinicService;

    @GetMapping(value="/findClinic")
    public ResponseEntity<?> findClinics(@RequestParam String date, @RequestParam String type) throws ParseException {
        List<ClinicDTO> clinics = new ArrayList<>();
        if(date.equals("")&&type.equals("")){
            List<Clinic> clinic = clinicService.findAll();
            for (Clinic c:clinic) {
                clinics.add(new ClinicDTO(c));
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
            System.out.println(a.getDate().toString()+" "+appointments.size());
            String temp =a.getDate().toString().substring(0,10);
            Date date2=new Date();
            date2 = formatter6.parse(temp);
            if (date2.equals(date1))
                apps.add(new AppointmentDTO(a.getDoctor(),a.getDate(),a.getType()));
        }
        List<String> names = new ArrayList<>();
        for (AppointmentDTO a:apps) {
            System.out.println(a.getDoctor().getClinic().getName());
            if(names.contains(a.getDoctor().getClinic().getName()))
                continue;
            names.add(a.getDoctor().getClinic().getName());
            clinics.add(new ClinicDTO(a.getDoctor().getClinic()));
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
        return new ResponseEntity<>(appDto, HttpStatus.OK);
    }

    @RequestMapping(value="/findClinic/doctors",method = RequestMethod.GET)
    public ResponseEntity<?> findDoctors(@RequestParam String clinicName,@RequestParam String date, @RequestParam String type){
        System.out.println(clinicName);
        SimpleDateFormat formatter6=new SimpleDateFormat("yyyy-MM-dd");
        Date date1=new Date();
        try {
            date1 = formatter6.parse(date);
        }
        catch(Exception e) {
            new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
        List<Appointment> appointments = appointmentService.findAppointments(type);
        List<AppointmentDTO> apps = new ArrayList<>();
        List<PersonDTO> doctors = new ArrayList<>();
        for (Appointment a:appointments) {
            apps.add(new AppointmentDTO(a.getDoctor(),a.getDate(),a.getType()));
        }
        List<String> names = new ArrayList<>();
        for (AppointmentDTO a:apps) {
           // System.out.println(a.getDoctor().getClinic().getName());
            if(names.contains(a.getDoctor().getFirstName()) || !a.getDoctor().getClinic().getName().equals(clinicName))
                continue;
            names.add(a.getDoctor().getFirstName());
            doctors.add(new PersonDTO(a.getDoctor()));
        }

        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }
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
        appointmentService.reserve(new PredefAppointmentDTORequest(request.patientId,id));
        return null;
    }

    static class Req{
        public int id;
        public Long patientId;
        public String type;
        public String date;
    }
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
     * funkcija koja preuzima sve predefinisane preglede,odnosno preglede na kojima nema pacijenta
     */
    @RequestMapping(value="/getPredefAppointment",method = RequestMethod.GET)
    public ResponseEntity<?> getPredefAppointments(){
        List<Appointment> apps =  appointmentService.findAll();
        List<AppointmentDTO> response = new ArrayList<>();
        for(Appointment a : apps){
            if(a.getPatient() == null){
                response.add(new AppointmentDTO(a.getId(),a.getDoctor(),a.getDate(),a.getType(),null,a.getPrice(),a.getDiscount(),a.getRoom().getName()));
            }
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @PostMapping(consumes = "application/json",value = "/reservePredef")
    public ResponseEntity<?> reservePredefApp(@RequestBody PredefAppointmentDTORequest request){
        appointmentService.reserve(request);
        return new ResponseEntity<>(request,HttpStatus.ACCEPTED);

    }
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,value = "/createPredef",method = RequestMethod.POST)
    public ResponseEntity<?> createPredef(@RequestBody CreatePredefDTO req){
        appointmentService.createPredef(req);
        return new ResponseEntity<>(null,HttpStatus.OK);
    }


}
