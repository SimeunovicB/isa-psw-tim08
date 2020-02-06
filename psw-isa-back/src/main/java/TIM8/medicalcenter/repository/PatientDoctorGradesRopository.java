package TIM8.medicalcenter.repository;

import TIM8.medicalcenter.model.grading.PatientDoctorGrades;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientDoctorGradesRopository  extends JpaRepository<PatientDoctorGrades,Long> {
}
