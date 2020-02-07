package TIM8.medicalcenter.controller;



import TIM8.medicalcenter.dto.AdministratorDTO;
import TIM8.medicalcenter.dto.DoctorDTO;
import TIM8.medicalcenter.dto.PersonDTO;
import TIM8.medicalcenter.model.users.Administrator;
import TIM8.medicalcenter.model.users.Doctor;
import TIM8.medicalcenter.model.users.Person;
import TIM8.medicalcenter.service.DoctorService;
import TIM8.medicalcenter.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "api/person",produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private DoctorService doctorService;


    @RequestMapping(consumes = "application/json",value="/getByEmail",method = RequestMethod.POST)
    public ResponseEntity<?> getByEmail(@RequestBody PersonDTO person) {
        Person personRet = personService.findOneByUsername(person.getUsername());
        if (personRet == null || personRet.getStatus().equals("DELETED"))
            return null;
        return new ResponseEntity<>(new PersonDTO(personRet), HttpStatus.OK);

    }

    @RequestMapping(value="/getAdminByEmail",method = RequestMethod.GET)
    public ResponseEntity<?> getAdminByEmail(@RequestParam String mail) {
        Administrator a = personService.findAdmin(mail).get(0);
        if (a == null)
            return null;
        return new ResponseEntity<>(new AdministratorDTO(a), HttpStatus.OK);

    }

    @RequestMapping(consumes = "application/json",value="/getDocByEmail",method = RequestMethod.POST)
    public ResponseEntity<?> getDocByEmail(@RequestBody PersonDTO person) {
        Doctor personRet = doctorService.findOneByUsername(person.getUsername());
        if (personRet == null || personRet.getStatus().equals("DELETED"))
            return null;
        return new ResponseEntity<>(new DoctorDTO(personRet.getFirstName(),personRet.getLastName(),personRet.getClinic().getId()), HttpStatus.OK);

    }

    @RequestMapping(value = "/getAllDoctors",method = RequestMethod.GET)
    public ResponseEntity<?> getAllDoctors() {
        List<Doctor> doctors = personService.findDoctors();
        List<PersonDTO> response = new ArrayList<>();
        for(Doctor d : doctors){
            if(!d.getStatus().equals("DELETED"))
                response.add(new PersonDTO((Person) d));
        }
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }

    @RequestMapping(consumes = "application/json",value ="/changePassword",method = RequestMethod.POST)
    public ResponseEntity<?> updatePassword(@RequestBody PasswordChanger passwordChanger) {
        personService.changePassword(passwordChanger.oldPassword, passwordChanger.newPassword);

        Map<String, String> result = new HashMap<>();
        result.put("result", "success");
        return ResponseEntity.accepted().body(result);
    }

    @PostMapping(consumes = "application/json", value = "/update")
    public ResponseEntity<PersonDTO> updateMedicalStaff(@RequestBody PersonDTO person){
        Person person1 = personService.findOneById(person.getId());
        if(person1.getId() != null){
            personService.updateUser(person.getFirstName(),person.getLastName(),person.getAddress(),person.getId());
            person1 = personService.findOneById(person.getId());
            return new ResponseEntity<>(new PersonDTO(person1),HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(new PersonDTO(),HttpStatus.BAD_REQUEST);
    }

    static class PasswordChanger {
        public String oldPassword;
        public String newPassword;
    }


}
