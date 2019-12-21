package TIM8.medicalcenter.controller;


import TIM8.medicalcenter.dto.AppointmentTypeDTO;
import TIM8.medicalcenter.model.AppointmentType;
import TIM8.medicalcenter.service.AppointmentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/appointmenttype")
public class AppointmentTypeController {

    @Autowired
    private AppointmentTypeService appointmentTypeService;

    @RequestMapping(value = "/addAppointmentType",consumes = "application/json", method = RequestMethod.POST)
    public ResponseEntity<?> addAppointmentType(@RequestBody AppointmentTypeDTO appointmentTypeDTO){
        AppointmentType appointmentType = appointmentTypeService.findOneByName(appointmentTypeDTO.getName());
        if(appointmentType!= null) {
            return new ResponseEntity<>(new AppointmentTypeDTO(appointmentType), HttpStatus.BAD_REQUEST);
        }
        appointmentType = appointmentTypeService.save(appointmentTypeDTO);
        return new ResponseEntity<>(new AppointmentTypeDTO(appointmentType), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/getAll")
    public ResponseEntity<?> getAllAppointmentTypes() {
        List<AppointmentType> appointmentTypes = appointmentTypeService.findAll();
        List<AppointmentTypeDTO> appointmentTypeDTOS = new ArrayList<>();
        for(AppointmentType m : appointmentTypes){
            appointmentTypeDTOS.add(new AppointmentTypeDTO(m));
        }
        return new ResponseEntity<>(appointmentTypeDTOS,HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", value = "/update")
    public ResponseEntity<AppointmentTypeDTO> updateAppointmentType(@RequestBody AppointmentTypeDTO appType){
        AppointmentType appointmentType = appointmentTypeService.findOneById(appType.getId());
        if(appointmentType.getId() != null){
            //appointmentTypeService.updateAppointmentType(appType.getName(),appType.getId());
            appointmentType = appointmentTypeService.findOneById(appType.getId());
            return new ResponseEntity<>(new AppointmentTypeDTO(appointmentType),HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(new AppointmentTypeDTO(),HttpStatus.BAD_REQUEST);
    }
}
