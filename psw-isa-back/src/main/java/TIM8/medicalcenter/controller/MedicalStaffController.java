package TIM8.medicalcenter.controller;

import TIM8.medicalcenter.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "api/medicalStaff")
public class MedicalStaffController {

    @Autowired
    PersonService personService;
    /*@PostMapping(consumes = "application/json",value = "/register")
    public ResponseEntity<Patient> Register(@RequestBody Patient patient) {
        if(personService.findOneByEmail(patient.getEmail())!=null)
            return null;
        patient.setStatus("PENDING");
        personService.save(patient);

        return new ResponseEntity<>(patient, HttpStatus.OK);

    }*/




}
