package TIM8.medicalcenter.controller;

import TIM8.medicalcenter.dto.MedicalRecordDTO;
import TIM8.medicalcenter.dto.PersonDTO;
import TIM8.medicalcenter.model.MedicalRecord;
import TIM8.medicalcenter.model.users.Patient;
import TIM8.medicalcenter.model.users.Person;
import TIM8.medicalcenter.service.MedicalRecordService;
import TIM8.medicalcenter.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/medicalRecord",produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicalRecordController {

    @Autowired
    MedicalRecordService medicalRecordService;

    @Autowired
    PersonService personService;

    @RequestMapping(value="/getByPatientId",method = RequestMethod.GET)
    public ResponseEntity<?> getById(@RequestParam Long id){
        Patient personRet = (Patient)personService.findOneById(id);
        MedicalRecord  mr = personRet.getMedicalRecord();
        if(mr == null){
            //TODO: napraviti novi medical record ako ga pacijent nema

        }

        MedicalRecordDTO md = new MedicalRecordDTO(mr,personRet);
        return new ResponseEntity<>(md,HttpStatus.OK);

    }

    @RequestMapping(consumes = "application/json",value = "/update",method = RequestMethod.POST)
    public ResponseEntity<?> update(@RequestBody MedicalRecordDTO recordDTO){
        medicalRecordService.updateMedicalRecord(recordDTO.getHeight(),recordDTO.getWeight(),recordDTO.getDiopter(), recordDTO.getAlergies(),recordDTO.getBloodType(),recordDTO.getId());
        return new ResponseEntity<>(new MedicalRecordDTO(),HttpStatus.OK);

    }
}
