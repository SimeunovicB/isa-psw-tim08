package TIM8.medicalcenter.service;

import TIM8.medicalcenter.model.AppointmentRequest;
import TIM8.medicalcenter.repository.AppointmentRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class AppointmentRequestService {
    @Autowired
    AppointmentRequestRepository appointmentRequestRepository;


    public List<AppointmentRequest> findAll() { return appointmentRequestRepository.findAll();}

    /**
     * Videti da li ima smisla staviti transakciju ovde
     * @param doctor
     * @param patient
     * @param date
     * @param type
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public AppointmentRequest save(Long doctor,Long patient , Date date,String type) {
        AppointmentRequest a= new AppointmentRequest();
        a.setDoctor_id(doctor);
        a.setPatient_id(patient);
        a.setDate(date);
        a.setAppointment_type(type);
        return appointmentRequestRepository.save(a);
    }

    public void delete(Long id) { appointmentRequestRepository.deleteById(id);}


}
