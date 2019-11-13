package TIM8.medicalcenter.controller;

import TIM8.medicalcenter.dto.PatientDTO;
import TIM8.medicalcenter.dto.PersonDTO;
import TIM8.medicalcenter.model.Patient;
import TIM8.medicalcenter.model.Person;
import TIM8.medicalcenter.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/patient")
public class PatientController {

    @Autowired
    private PersonService personService;

    @PostMapping(consumes = "application/json",value = "/register")
    public ResponseEntity<Patient> Register(@RequestBody Patient patient) {
        if(personService.findOneByEmail(patient.getEmail())!=null)
            return null;
        patient.setStatus("Pending");
        personService.save(patient);

        return new ResponseEntity<>(patient, HttpStatus.OK);

    }
}
