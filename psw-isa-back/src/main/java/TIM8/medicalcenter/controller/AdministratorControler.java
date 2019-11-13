package TIM8.medicalcenter.controller;

import TIM8.medicalcenter.dto.ClinicDTO;
import TIM8.medicalcenter.dto.PatientDTO;
import TIM8.medicalcenter.model.Patient;
import TIM8.medicalcenter.model.Person;
import TIM8.medicalcenter.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/administrator")
public class AdministratorControler {

    @Autowired
    PersonService personService;

    @GetMapping
    public ResponseEntity<List<PatientDTO>> getClinics() {
        List<Person> patientList = personService.findByType("P");
        List<PatientDTO> patients = new ArrayList<>();
        for(Person p : patientList){
            patients.add(new PatientDTO((Patient) p));
        }
        return new ResponseEntity<>(patients,HttpStatus.OK);
    }
}



