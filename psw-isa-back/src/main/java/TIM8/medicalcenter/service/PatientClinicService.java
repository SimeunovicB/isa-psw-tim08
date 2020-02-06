package TIM8.medicalcenter.service;

import TIM8.medicalcenter.model.grading.PatientClinicGrades;
import TIM8.medicalcenter.repository.PatientClinicGradesRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PatientClinicService {
    @Autowired
    private PatientClinicGradesRepository appointmentRepository;

    public PatientClinicGrades save(PatientClinicGrades a) {  return appointmentRepository.save(a); }

}
