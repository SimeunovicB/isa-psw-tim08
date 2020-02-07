package TIM8.medicalcenter.repository;

import TIM8.medicalcenter.model.Appointment;
import TIM8.medicalcenter.model.grading.PatientDoctorGrades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientDoctorGradesRepository extends JpaRepository<PatientDoctorGrades,Long> {

    @Query("SELECT pdg from PatientDoctorGrades pdg where pdg.doctor.id=:id")
    List<PatientDoctorGrades> findDoctorGrades(@Param("id") Long id);

    List<PatientDoctorGrades> findAll();
}
