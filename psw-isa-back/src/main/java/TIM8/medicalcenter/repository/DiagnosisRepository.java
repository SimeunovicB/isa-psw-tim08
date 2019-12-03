package TIM8.medicalcenter.repository;

import TIM8.medicalcenter.model.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.DataInput;
import java.util.List;

public interface DiagnosisRepository extends JpaRepository<Diagnosis,Long> {

    List<Diagnosis> findAll();
    Diagnosis findByName(String name);
}
