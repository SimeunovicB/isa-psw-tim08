package TIM8.medicalcenter.service;

import TIM8.medicalcenter.dto.DiagnosisDTO;
import TIM8.medicalcenter.model.Diagnosis;
import TIM8.medicalcenter.repository.DiagnosisRepository;
import TIM8.medicalcenter.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnosisService {
    @Autowired
    private DiagnosisRepository diagnosisRepository;

    public Diagnosis findOneByName(String name) {return diagnosisRepository.findByName(name);}
    public List<Diagnosis> findAll() { return diagnosisRepository.findAll();}
    public Diagnosis save(DiagnosisDTO diagnosisDTO) {
        Diagnosis d = new Diagnosis();
        d.setName(diagnosisDTO.getName());
        return diagnosisRepository.save(d);
    }
}
