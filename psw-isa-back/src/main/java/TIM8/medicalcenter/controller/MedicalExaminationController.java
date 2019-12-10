package TIM8.medicalcenter.controller;

import TIM8.medicalcenter.dto.MedicalExaminationDTO;
import TIM8.medicalcenter.model.MedicalExaminationReport;
import TIM8.medicalcenter.service.MedicalExaminationReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/api/medicalExamination")
public class MedicalExaminationController {

    @Autowired
    MedicalExaminationReportService medicalExaminationReportService;

    @RequestMapping(value = "/new",consumes = "application/json")
    public ResponseEntity<?> addNewReport(@RequestBody MedicalExaminationDTO medicalExaminationDTO){
        MedicalExaminationReport medicalExaminationReport = medicalExaminationReportService.createNew(medicalExaminationDTO);
        return new ResponseEntity<>(medicalExaminationReport, HttpStatus.CREATED);

    }
}
