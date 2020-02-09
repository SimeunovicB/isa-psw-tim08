package TIM8.medicalcenter.controller;

import TIM8.medicalcenter.dto.MedicineDTO;
import TIM8.medicalcenter.model.Medicine;
import TIM8.medicalcenter.service.MedicineService;
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
@RequestMapping(value = "api/medicine")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    /**
     * Funkcija kojom administrator klinike dodaje nove lekove
     * @param medicineDTO
     * @return
     */
    @PreAuthorize("hasRole('CCADMIN')")
    @RequestMapping(value = "/addMedicine",consumes = "application/json")
    public ResponseEntity<?> addMedicine(@RequestBody MedicineDTO medicineDTO){
        Medicine medicine = medicineService.findOneByName(medicineDTO.getName());
        if(medicine != null) {
            return new ResponseEntity<>(new MedicineDTO(medicine),HttpStatus.BAD_REQUEST);
        }
        medicine = medicineService.save(medicineDTO);
        return new ResponseEntity<>(new MedicineDTO(medicine), HttpStatus.CREATED);
    }

    /**
     * Dobavljanje svih lekova dostupna svima zbog potreba frontenda
     * @return
     */
    @RequestMapping(value = "/getAll")
    public ResponseEntity<?> getAllMedicine() {
        List<Medicine> medicines = medicineService.findAll();
        List<MedicineDTO> medicineDTOS = new ArrayList<>();
        for(Medicine m : medicines){
            medicineDTOS.add(new MedicineDTO(m));
        }
        return new ResponseEntity<>(medicineDTOS,HttpStatus.OK);
    }
}
