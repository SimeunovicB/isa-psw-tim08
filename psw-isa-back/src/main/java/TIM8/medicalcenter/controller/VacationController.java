package TIM8.medicalcenter.controller;


import TIM8.medicalcenter.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/vacation",produces = MediaType.APPLICATION_JSON_VALUE)
public class VacationController {

    @Autowired
    private VacationService vacationService;

    /*@GetMapping(value="/getClinics")
    public ResponseEntity<List<VacationD>> getAllClinics() {
        List<Vacation> vacations = vacationService.findAll();
        List<ClinicDTO> allClinics = new ArrayList<>();
        for(Clinic c : clinics){
            allClinics.add(new ClinicDTO(c));
        }
        return new ResponseEntity<>(allClinics, HttpStatus.OK);
    }*/




}
