package TIM8.medicalcenter.controller;


import TIM8.medicalcenter.dto.ClinicDTO;
import TIM8.medicalcenter.dto.PersonDTO;
import TIM8.medicalcenter.model.Clinic;
import TIM8.medicalcenter.model.Patient;
import TIM8.medicalcenter.model.Person;
import TIM8.medicalcenter.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@RestController
@RequestMapping(value = "api/person")
public class PersonController {

    @Autowired
    private PersonService personService;



    @PostMapping(consumes = "application/json",value = "/login")
    public ResponseEntity<PersonDTO> Login(@RequestBody PersonDTO person) {
        Person person1 = personService.findOneByEmail(person.getEmail());
        if(person1==null)
            return null;
        if(person1.getPassword().equals(person.getPassword())){
            PersonDTO p= new PersonDTO(person1);
            return new ResponseEntity<>(p, HttpStatus.OK);
        }
            return  null;
    }
}