package TIM8.medicalcenter.controller;

import TIM8.medicalcenter.dto.MedicalRecordDTO;
import TIM8.medicalcenter.dto.PersonDTO;
import TIM8.medicalcenter.model.MedicalRecord;
import TIM8.medicalcenter.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/medicalRecord",produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicalRecordController {

    @Autowired
    MedicalRecordService medicalRecordService;

    @RequestMapping(consumes = "application/json",value = "/getByPatientId",method = RequestMethod.POST)
    public ResponseEntity<?> getByPatientId(@RequestBody PersonDTO person){
        MedicalRecord medicalRecord = medicalRecordService.findByPatientId(person.getId());
        return new ResponseEntity<>(new MedicalRecordDTO(medicalRecord), HttpStatus.OK);
    }

    @RequestMapping(consumes = "application/json",value = "/update",method = RequestMethod.POST)
    public ResponseEntity<?> update(@RequestBody MedicalRecordDTO recordDTO){
        medicalRecordService.updateMedicalRecord(recordDTO.getHeight(),recordDTO.getWeight(),recordDTO.getDiopter(), recordDTO.getAlergies(),recordDTO.getBloodType(),recordDTO.getId());
        return new ResponseEntity<>(new MedicalRecordDTO(medicalRecordService.findOneById(recordDTO.getId())),HttpStatus.OK);

    }
}
