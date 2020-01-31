package TIM8.medicalcenter.service;

import TIM8.medicalcenter.model.Appointment;
import TIM8.medicalcenter.model.users.Doctor;
import TIM8.medicalcenter.repository.AppointmentRepository;
import TIM8.medicalcenter.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppointmentService  {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PersonRepository personRepository;


    public List<Appointment> findAll(){return appointmentRepository.findAll();}
    public List<Appointment> findAppointments(Date date, String type){return appointmentRepository.findAppointments(date,type);}
    public Appointment save(Appointment a) {  return appointmentRepository.save(a); }
    public List<Appointment> findAllByDoctorId(Long id){
        Doctor d = (Doctor) personRepository.findOneById(id);
        return appointmentRepository.findAllByDoctor(d);
    }
}
