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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/medicalRecord",produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicalRecordController {

    @Autowired
    MedicalRecordService medicalRecordService;

    @Autowired
    PersonService personService;

    /**
     * Funkcija kojom doktor ili pacijent mogu da vide informacije o pacijentovom zdravstvenom kartonu
     * @param id
     * @return
     */
    @PreAuthorize("hasAnyRole('PATIENT','MEDICAL_STAFF')")
    @RequestMapping(value="/getByPatientId",method = RequestMethod.GET)
    public ResponseEntity<?> getById(@RequestParam Long id){
        Patient personRet = (Patient)personService.findOneById(id);
        MedicalRecord  mr = personRet.getMedicalRecord();
        MedicalRecordDTO md = new MedicalRecordDTO(mr,personRet);
        return new ResponseEntity<>(md,HttpStatus.OK);

    }

    /**
     * Funkcija kojom doktor menja podatke u pacijentovom zdravstvenom kartonu
     * @param recordDTO
     * @return
     */
    @PreAuthorize("hasRole('MEDICAL_STAFF')")
    @RequestMapping(consumes = "application/json",value = "/update",method = RequestMethod.POST)
    public ResponseEntity<?> update(@RequestBody MedicalRecordDTO recordDTO){
        medicalRecordService.updateMedicalRecord(recordDTO.getHeight(),recordDTO.getWeight(),recordDTO.getDiopter(), recordDTO.getAlergies(),recordDTO.getBloodType(),recordDTO.getId());
        return new ResponseEntity<>(new MedicalRecordDTO(),HttpStatus.OK);

    }
}
