package TIM8.medicalcenter.controller;

import TIM8.medicalcenter.dto.ClinicDTO;
import TIM8.medicalcenter.dto.PatientDTO;
import TIM8.medicalcenter.dto.PersonDTO;
import TIM8.medicalcenter.model.Patient;
import TIM8.medicalcenter.model.Person;
import TIM8.medicalcenter.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.Response;
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
            if(p.getStatus().equalsIgnoreCase("PENDING")){
                patients.add(new PatientDTO((Patient) p));

            }

        }
        return new ResponseEntity<>(patients,HttpStatus.OK);
    }
    @PutMapping(consumes = "application/json",value ="/approveRegistration/{id}")
    public ResponseEntity<PersonDTO> updateStatusApproved(@PathVariable Long id) {

        Person person = personService.findOneById(id);
        if(person != null && person.getDecriminatorValue().equals("P") && person.getStatus().equalsIgnoreCase("PENDING")){
            personService.updatePersonStatus("Active",person.getId());
            //TODO: slanje maila korisniku
            return new ResponseEntity<>(new PersonDTO(person),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new PersonDTO(),HttpStatus.NO_CONTENT);
        }
    }
    @PutMapping(consumes = "application/json",value = "/rejectedRegistration/{id}")
    public ResponseEntity<PersonDTO> updateStatusRejected(@PathVariable Long id){
        Person person = personService.findOneById(id);
        if(person != null && person.getDecriminatorValue().equals("P") && person.getStatus().equalsIgnoreCase("PENDING")){
            personService.updatePersonStatus("Rejected",person.getId());
            return new ResponseEntity<>(new PersonDTO(person),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new PersonDTO(),HttpStatus.NO_CONTENT);
        }

    }



}



