package TIM8.medicalcenter.service;

import TIM8.medicalcenter.model.Clinic;
import TIM8.medicalcenter.repository.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicService {

    @Autowired
    ClinicRepository clinicRepository;

    @Cacheable("clinics")
    public List<Clinic> findAll() {
        return clinicRepository.findAll();
    }
    @Cacheable("clinics")
    public Clinic findOneById(Long id){
        return clinicRepository.findOneById(id);
    }
    @Cacheable("clinics")
    public Clinic findOneByName(String name){return clinicRepository.findOneByName(name); }
    public Clinic save(Clinic c) {return clinicRepository.save(c);}
    public int updateClinic(String name,String address,String description,long id) { return clinicRepository.updateClinic(name,address,description,id); }
}
