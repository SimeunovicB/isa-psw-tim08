package TIM8.medicalcenter.repository;

import TIM8.medicalcenter.model.users.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    List<Doctor> findAll();

    Doctor findOneById(Long id);
    Doctor findOneByUsername(String username);
}
