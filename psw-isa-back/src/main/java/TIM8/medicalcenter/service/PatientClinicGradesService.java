package TIM8.medicalcenter.service;

import TIM8.medicalcenter.model.grading.PatientClinicGrades;
import TIM8.medicalcenter.repository.PatientClinicGradesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientClinicGradesService {

    @Autowired
    private PatientClinicGradesRepository patientClinicGradesRepository;

    public List<PatientClinicGrades> findClinicGrades(Long id) { return patientClinicGradesRepository.findClinicGrades(id); }
}
