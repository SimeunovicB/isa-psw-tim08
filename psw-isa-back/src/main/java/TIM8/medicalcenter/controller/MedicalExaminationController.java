package TIM8.medicalcenter.controller;

import TIM8.medicalcenter.dto.MedicalExaminationDTO;
import TIM8.medicalcenter.dto.response.MedicalExaminationResponseDTO;
import TIM8.medicalcenter.model.MedicalExaminationReport;
import TIM8.medicalcenter.service.MedicalExaminationReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

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
    @RequestMapping(value = "/getAll/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> getAllForSiglePatient(@PathVariable Long id){

        Set<MedicalExaminationReport> medicalExaminationReports = medicalExaminationReportService.findByPatientId(id);
        Set<MedicalExaminationResponseDTO> medicalExaminationResponseDTOSet = new HashSet<>();
        for(MedicalExaminationReport m : medicalExaminationReports){
            medicalExaminationResponseDTOSet.add(new MedicalExaminationResponseDTO(m));
        }
        return new ResponseEntity<>(medicalExaminationResponseDTOSet,HttpStatus.OK);
    }
}
