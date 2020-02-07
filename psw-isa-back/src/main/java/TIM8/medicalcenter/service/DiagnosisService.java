package TIM8.medicalcenter.service;

import TIM8.medicalcenter.dto.DiagnosisDTO;
import TIM8.medicalcenter.model.Diagnosis;
import TIM8.medicalcenter.repository.DiagnosisRepository;
import TIM8.medicalcenter.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DiagnosisService {
    @Autowired
    private DiagnosisRepository diagnosisRepository;

    public Diagnosis findOneByName(String name) {return diagnosisRepository.findByName(name);}
    public Diagnosis findOneById(Long id) {return diagnosisRepository.getOne(id);}
    public List<Diagnosis> findAll() { return diagnosisRepository.findAll();}
    /**
     * Potrebno je enkapsulirati ovo funkcionalnost u transakciju zato sto moze doci do situacije u kojoj dva administratora u isto vremen pokusavaju da izmene istu diagnozu
     * @param diagnosisDTO
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Diagnosis save(DiagnosisDTO diagnosisDTO) {
        Diagnosis d = new Diagnosis();
        d.setName(diagnosisDTO.getName());
        return diagnosisRepository.save(d);
    }
}
