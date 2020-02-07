package TIM8.medicalcenter.repository;

import TIM8.medicalcenter.model.grading.PatientClinicGrades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientClinicGradesRepository extends JpaRepository<PatientClinicGrades,Long> {

    @Query("SELECT pcg from PatientClinicGrades pcg where pcg.clinic.id=:id")
    List<PatientClinicGrades> findClinicGrades(@Param("id") Long id);


    List<PatientClinicGrades> findAll();

}
