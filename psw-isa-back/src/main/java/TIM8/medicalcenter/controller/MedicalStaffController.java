package TIM8.medicalcenter.controller;

import TIM8.medicalcenter.dto.PersonDTO;
import TIM8.medicalcenter.model.Person;
import TIM8.medicalcenter.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@CrossOrigin
@RestController
@RequestMapping(value = "api/medicalStaff")
public class MedicalStaffController {

    @Autowired
    PersonService personService;

}
