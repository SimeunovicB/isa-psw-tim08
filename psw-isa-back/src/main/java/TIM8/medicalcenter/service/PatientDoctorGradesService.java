package TIM8.medicalcenter.service;

import TIM8.medicalcenter.model.grading.PatientDoctorGrades;
import TIM8.medicalcenter.repository.PatientDoctorGradesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PatientDoctorGradesService {

    @Autowired
    private PatientDoctorGradesRepository patientDoctorGradesRepository;

    public List<PatientDoctorGrades> findDoctorGrades(Long id) { return patientDoctorGradesRepository.findDoctorGrades(id); }
    public PatientDoctorGrades save(PatientDoctorGrades a) {  return patientDoctorGradesRepository.save(a); }
}
