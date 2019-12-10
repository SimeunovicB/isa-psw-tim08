package TIM8.medicalcenter.service;

import TIM8.medicalcenter.dto.MedicineDTO;
import TIM8.medicalcenter.model.Medicine;
import TIM8.medicalcenter.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService {
    @Autowired
    private MedicineRepository medicineRepository;

    public Medicine findOneByName(String name) {return medicineRepository.findByName(name);}
    public Medicine findOneById(Long id) {return medicineRepository.getOne(id);}
    public List<Medicine> findAll() { return medicineRepository.findAll();}
    public Medicine save(MedicineDTO medicineDTO) {
        Medicine m = new Medicine();
        m.setName(medicineDTO.getName());
        return medicineRepository.save(m);
    }
}
