package TIM8.medicalcenter.service;

import TIM8.medicalcenter.model.AppointmentRequest;
import TIM8.medicalcenter.repository.AppointmentRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentRequestService {
    @Autowired
    AppointmentRequestRepository appointmentRequestRepository;


    public List<AppointmentRequest> findAll() { return appointmentRequestRepository.findAll();}
    public void delete(Long id) { appointmentRequestRepository.deleteById(id);}

}
