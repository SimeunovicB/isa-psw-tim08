package TIM8.medicalcenter.controller;

import TIM8.medicalcenter.dto.MedicalExaminationDTO;
import TIM8.medicalcenter.model.MedicalExaminationReport;
import TIM8.medicalcenter.service.MedicalExaminationReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/medicalExamination")
public class MedicalExaminationController {

    @Autowired
    MedicalExaminationReportService medicalExaminationReportService;

    @RequestMapping(value = "/new",consumes = "application/json" ,method = RequestMethod.POST)
    public ResponseEntity<?> addNewReport(@RequestBody MedicalExaminationDTO medicalExaminationDTO){
        MedicalExaminationReport medicalExaminationReport = medicalExaminationReportService.createNew(medicalExaminationDTO);
        return new ResponseEntity<>(new MedicalExaminationDTO(medicalExaminationReport), HttpStatus.CREATED);

    }
}
