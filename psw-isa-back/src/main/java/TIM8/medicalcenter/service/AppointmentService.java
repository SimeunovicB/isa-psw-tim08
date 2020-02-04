package TIM8.medicalcenter.service;

import TIM8.medicalcenter.dto.Request.PredefAppointmentDTORequest;
import TIM8.medicalcenter.model.Appointment;
import TIM8.medicalcenter.model.users.Doctor;
import TIM8.medicalcenter.model.users.Patient;
import TIM8.medicalcenter.repository.AppointmentRepository;
import TIM8.medicalcenter.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Appointment reserve(PredefAppointmentDTORequest a){
        Appointment a1 = appointmentRepository.findOneById(a.getAppointmentId());
        Patient p = (Patient) personRepository.findOneById(a.getPatientId());
        a1.setPatient(p);
        Appointment a2  = appointmentRepository.save(a1);
        return a2;
    }
}
