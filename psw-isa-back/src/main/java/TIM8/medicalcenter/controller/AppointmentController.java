package TIM8.medicalcenter.controller;

import TIM8.medicalcenter.dto.AppointmentDTO;
import TIM8.medicalcenter.dto.ClinicDTO;
import TIM8.medicalcenter.dto.PatientDTO;
import TIM8.medicalcenter.model.Appointment;
import TIM8.medicalcenter.model.Clinic;
import TIM8.medicalcenter.model.Users.Patient;
import TIM8.medicalcenter.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "api/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @RequestMapping(consumes = "application/json",value="/findClinic",method = RequestMethod.GET)
    public ResponseEntity<?> findPatient(@RequestParam String date, @RequestParam String type){
        System.out.println(date+" "+type);
        SimpleDateFormat formatter6=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1=new Date();
        try {
            date1 = formatter6.parse(date);
        }
        catch(Exception e) {
            System.out.println('a');
        }
        List<Appointment> appointments = appointmentService.findAppointments(date1,type);
        System.out.println(appointments);
        List<AppointmentDTO> apps = new ArrayList<>();
        List<ClinicDTO> clinics = new ArrayList<>();
        for (Appointment a:appointments) {
            apps.add(new AppointmentDTO(a));

        }
        for (AppointmentDTO a:apps) {
            if(clinics.contains(a.getDoctor().getClinic()))
                continue;
            clinics.add(new ClinicDTO(a.getDoctor().getClinic()));
        }

        return new ResponseEntity<>(clinics, HttpStatus.OK);
    }
}
