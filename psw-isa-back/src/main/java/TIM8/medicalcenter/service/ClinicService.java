package TIM8.medicalcenter.service;

import TIM8.medicalcenter.model.Clinic;
import TIM8.medicalcenter.repository.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicService {

    @Autowired
    ClinicRepository clinicRepository;

    public List<Clinic> findAll() {
        return clinicRepository.findAll();
    }
    public Clinic findOneById(Long id){
        return clinicRepository.findOneById(id);
    }
}
