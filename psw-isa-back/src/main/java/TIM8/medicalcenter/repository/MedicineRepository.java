package TIM8.medicalcenter.repository;

import TIM8.medicalcenter.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicineRepository extends JpaRepository<Medicine,Long> {

    List<Medicine> findAll();
    Medicine findByName(String name);
}
