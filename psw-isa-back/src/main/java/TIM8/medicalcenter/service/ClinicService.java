package TIM8.medicalcenter.service;

import TIM8.medicalcenter.model.Clinic;
import TIM8.medicalcenter.repository.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClinicService {

    @Autowired
    ClinicRepository clinicRepository;

    @Cacheable("clinics")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
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

    /**
     * Potrebno je enkapsulirati ovo funkcionalnost u transakciju zato sto moze doci do situacije u kojoj dva administratora u isto vremen pokusavaju da izmene istu kliniku
     * @param name
     * @param address
     * @param description
     * @param id
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int updateClinic(String name,String address,String description,long id) {
        Clinic c = clinicRepository.findOneById(id);
        c.setName(name);
        c.setAddress(address);
        c.setDescription(description);
        clinicRepository.save(c);
        return 1;
    }
}
