package TIM8.medicalcenter.controller;

import TIM8.medicalcenter.dto.DiagnosisDTO;
import TIM8.medicalcenter.model.Diagnosis;
import TIM8.medicalcenter.service.DiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/diagnosis")
public class DiagnosisController {
    @Autowired
    private DiagnosisService diagnosisService;

    /**
     * Funkcija kojom administrator dodaje dijagnoze
     * @param diagnosisDTO
     * @return
     */
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/addDiagnosis",consumes = "application/json")
    public ResponseEntity<?> addMedicine(@RequestBody DiagnosisDTO diagnosisDTO){
        Diagnosis diagnosis= diagnosisService.findOneByName(diagnosisDTO.getName());
        if(diagnosis != null) {
            return new ResponseEntity<>(new DiagnosisDTO(diagnosis), HttpStatus.BAD_REQUEST);
        }
        diagnosis = diagnosisService.save(diagnosisDTO);
        return new ResponseEntity<>(new DiagnosisDTO(diagnosis), HttpStatus.CREATED);
    }

    /**
     * Funkcija kojom se dobavljaju sve dijagnoze,dostupna svima jer je potrebna na frontendu
     * @return
     */
    @RequestMapping(value = "/getAll")
    public ResponseEntity<?> getAllMedicine() {
        List<Diagnosis> diagnosises = diagnosisService.findAll();
        List<DiagnosisDTO> diagnosisDTOS = new ArrayList<>();
        for(Diagnosis d : diagnosises){
            diagnosisDTOS.add(new DiagnosisDTO(d));
        }
        return new ResponseEntity<>(diagnosisDTOS,HttpStatus.OK);
    }
}
