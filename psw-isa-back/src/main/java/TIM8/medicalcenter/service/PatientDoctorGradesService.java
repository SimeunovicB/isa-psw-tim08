package TIM8.medicalcenter.service;

import TIM8.medicalcenter.model.grading.PatientDoctorGrades;
import TIM8.medicalcenter.repository.PatientDoctorGradesRopository;
import org.springframework.beans.factory.annotation.Autowired;

public class PatientDoctorGradesService {
    @Autowired
    private PatientDoctorGradesRopository appointmentRepository;

    public PatientDoctorGrades save(PatientDoctorGrades a) {  return appointmentRepository.save(a); }

}
