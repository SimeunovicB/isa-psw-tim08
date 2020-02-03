package TIM8.medicalcenter.service;

import TIM8.medicalcenter.model.users.Doctor;
import TIM8.medicalcenter.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor findOneByUsername(String username) { return doctorRepository.findOneByUsername(username); }
}
