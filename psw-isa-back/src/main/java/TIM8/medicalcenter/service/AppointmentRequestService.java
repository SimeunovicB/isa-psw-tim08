package TIM8.medicalcenter.service;

import TIM8.medicalcenter.model.AppointmentRequest;
import TIM8.medicalcenter.repository.AppointmentRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppointmentRequestService {
    @Autowired
    AppointmentRequestRepository appointmentRequestRepository;


    public List<AppointmentRequest> findAll() { return appointmentRequestRepository.findAll();}
    public AppointmentRequest save(Long doctor,Long patient , Date date,String type) {
        AppointmentRequest a= new AppointmentRequest();
        a.setDoctor_id(doctor);
        a.setPatient_id(patient);
        a.setDate(date);
        a.setAppointment_type(type);
        return appointmentRequestRepository.save(a);
    }

}
