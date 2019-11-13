package TIM8.medicalcenter.repository;

import TIM8.medicalcenter.model.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClinicRepository extends JpaRepository<Clinic,Long> {
    List<Clinic> findAll();

    Clinic findOneById(Long id);
}
