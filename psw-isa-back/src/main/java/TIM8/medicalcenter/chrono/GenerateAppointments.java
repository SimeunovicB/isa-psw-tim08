package TIM8.medicalcenter.chrono;

import TIM8.medicalcenter.model.Appointment;
import TIM8.medicalcenter.model.AppointmentRequest;
import TIM8.medicalcenter.model.Room;
import TIM8.medicalcenter.model.users.Doctor;
import TIM8.medicalcenter.model.users.Patient;
import TIM8.medicalcenter.service.AppointmentRequestService;
import TIM8.medicalcenter.service.AppointmentService;
import TIM8.medicalcenter.service.PersonService;
import TIM8.medicalcenter.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.*;


public class GenerateAppointments {

    @Autowired
    private AppointmentRequestService appointmentRequestService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PersonService personService;









}
