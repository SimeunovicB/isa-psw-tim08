package TIM8.medicalcenter.service;

import TIM8.medicalcenter.model.Appointment;
import TIM8.medicalcenter.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppointmentService  {
    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Appointment> findAll(){return appointmentRepository.findAll();}
    public List<Appointment> findAppointments(Date date, String type){return appointmentRepository.findAppointments(date,type);}
}
