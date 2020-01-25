package TIM8.medicalcenter.controller;

import TIM8.medicalcenter.model.Appointment;
import TIM8.medicalcenter.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/predefAppointment")
public class PredefAppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @RequestMapping(consumes = "application/json",value="/reserveAppointments",method = RequestMethod.GET)
    public ResponseEntity<?> reserveAppointment(@RequestParam String date, @RequestParam String type, @RequestParam String doctorName,
                                                @RequestParam String patient){
        List<Appointment> apps = appointmentService.findAll();
        for (Appointment a:apps) {
            if(a.getDate().equals(date)&& a.getType().equalsIgnoreCase(type)&&a.getDoctor().getFirstName().equalsIgnoreCase(doctorName))
                a.setStatus(patient);
        }
        return new ResponseEntity<>(apps, HttpStatus.OK);
    }

}
