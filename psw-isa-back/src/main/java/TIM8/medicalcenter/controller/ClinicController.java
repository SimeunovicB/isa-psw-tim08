package TIM8.medicalcenter.controller;

import TIM8.medicalcenter.dto.ClinicDTO;
import TIM8.medicalcenter.model.Clinic;
import TIM8.medicalcenter.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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




}
