package TIM8.medicalcenter.controller;


import TIM8.medicalcenter.dto.ClinicDTO;
import TIM8.medicalcenter.dto.PersonDTO;
import TIM8.medicalcenter.model.Clinic;
import TIM8.medicalcenter.model.Person;
import TIM8.medicalcenter.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/person")
public class PersonController {

    @Autowired
    private PersonService personService;



    @PostMapping(consumes = "application/json")
    public ResponseEntity<PersonDTO> getClinics(@RequestBody Person person) {
        Person person1 = (Person) personService.findOneByEmail(person.getEmail());
        System.out.println(person1.getPassword());
        PersonDTO p= new PersonDTO(person);
        return new ResponseEntity<>(p, HttpStatus.OK);


    }
}
