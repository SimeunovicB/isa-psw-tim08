package TIM8.medicalcenter.repository;

import TIM8.medicalcenter.model.grading.PatientClinicGrades;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientClinicGradesRepository extends JpaRepository<PatientClinicGrades,Long> {
}
