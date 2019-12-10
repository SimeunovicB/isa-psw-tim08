package TIM8.medicalcenter.controller;

import TIM8.medicalcenter.dto.ClinicDTO;
import TIM8.medicalcenter.exception.ResourceConflictException;
import TIM8.medicalcenter.model.Clinic;
import TIM8.medicalcenter.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "api/clinic")
public class ClinicController {
    @Autowired
    private ClinicService clinicService;

    @GetMapping
    public ResponseEntity<ClinicDTO> getClinics() {
        Clinic c = clinicService.findOneById((long)1);
        ClinicDTO clinicDTO = new ClinicDTO(c);
        return new ResponseEntity<>(clinicDTO,HttpStatus.OK);
    }
    @GetMapping(value="/getClinics")
    public ResponseEntity<List<ClinicDTO>> getAllClinics() {
        List<Clinic> clinics = clinicService.findAll();
        List<ClinicDTO> allClinics = new ArrayList<>();
        for(Clinic c : clinics){
            allClinics.add(new ClinicDTO(c));
        }
        return new ResponseEntity<>(allClinics,HttpStatus.OK);
    }
    @RequestMapping(value="/addClinic",consumes = "application/json",method = RequestMethod.POST)
    public ResponseEntity<?> addClinic(@RequestBody Clinic clinic){
        Clinic clinic1 = clinicService.findOneByName(clinic.getName());
        if(clinic1 != null){
            throw new ResourceConflictException(clinic1.getId(), "Clinic already exists");
        }
        clinicService.save(clinic);
        return new ResponseEntity<>(new ClinicDTO(clinic), HttpStatus.CREATED);
    }

}
