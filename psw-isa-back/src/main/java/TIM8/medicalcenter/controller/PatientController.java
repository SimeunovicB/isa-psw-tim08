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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "api/patient")
public class PatientController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public ResponseEntity<List<PatientDTO>> getPatients() {
        List<Person> patientList = personService.findByType("P");
        List<PatientDTO> patients = new ArrayList<>();
        for(Person p : patientList){
            if(p.getStatus().equalsIgnoreCase("PENDING")){
                patients.add(new PatientDTO((Patient) p));

            }

        }
        return new ResponseEntity<>(patients,HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json",value = "/register")
    public ResponseEntity<Patient> Register(@RequestBody Patient patient) {
        if(personService.findOneByEmail(patient.getEmail())!=null)
            return null;
        patient.setStatus("PENDING");
        personService.save(patient);

        return new ResponseEntity<>(patient, HttpStatus.OK);

    }
}
