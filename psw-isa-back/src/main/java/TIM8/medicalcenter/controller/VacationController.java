package TIM8.medicalcenter.controller;


import TIM8.medicalcenter.dto.VacationRequestsDTO;

import TIM8.medicalcenter.model.Vacation;
import TIM8.medicalcenter.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping(value = "api/vacation",produces = MediaType.APPLICATION_JSON_VALUE)
public class VacationController {

    @Autowired
    private VacationService vacationService;

    @RequestMapping(consumes = "application/json",value = "/pendingVacations",method = RequestMethod.GET)
    public ResponseEntity<?> getPending(){
        List<VacationRequestsDTO> vacationList = vacationService.findByStatusWithDoctorInfo("PENDING");
        return new ResponseEntity<>(vacationList, HttpStatus.OK);
    }




}
