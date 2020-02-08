package TIM8.medicalcenter.controller;


import TIM8.medicalcenter.dto.AppointmentTypeDTO;
import TIM8.medicalcenter.model.Appointment;
import TIM8.medicalcenter.model.AppointmentType;
import TIM8.medicalcenter.service.AppointmentService;
import TIM8.medicalcenter.service.AppointmentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "api/appointmenttype")
public class AppointmentTypeController {

    @Autowired
    private AppointmentTypeService appointmentTypeService;

    @Autowired
    private AppointmentService appointmentService;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/addAppointmentType",consumes = "application/json", method = RequestMethod.POST)
    public ResponseEntity<?> addAppointmentType(@RequestBody AppointmentTypeDTO appointmentTypeDTO){
        AppointmentType appointmentType = appointmentTypeService.findOneByName(appointmentTypeDTO.getName());
        if(appointmentType!= null) {
            return new ResponseEntity<>(new AppointmentTypeDTO(appointmentType), HttpStatus.BAD_REQUEST);
        }
        appointmentType = appointmentTypeService.save(appointmentTypeDTO);
        return new ResponseEntity<>(new AppointmentTypeDTO(appointmentType), HttpStatus.CREATED);
    }

    /**
     * Funkcija kojom se dobavljaju svi tipovi pregleda,moze da je koristi svako
     * @return
     */
    @RequestMapping(value = "/getAll")
    public ResponseEntity<?> getAllAppointmentTypes() {
        List<AppointmentType> appointmentTypes = appointmentTypeService.findAll();
        List<AppointmentTypeDTO> appointmentTypeDTOS = new ArrayList<>();
        for(AppointmentType m : appointmentTypes){
            appointmentTypeDTOS.add(new AppointmentTypeDTO(m));
        }
        return new ResponseEntity<>(appointmentTypeDTOS,HttpStatus.OK);
    }

    /**
     * Funkcija kojom administrator klinike moze da menja tipove pregleda
     * @param appType
     * @return
     */
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(consumes = "application/json", value = "/update", method=RequestMethod.POST)
    public ResponseEntity<?> updateAppointmentType(@RequestBody AppointmentTypeDTO appType){
        int a = appointmentTypeService.updateAppointmentType(appType.getName(), appType.getId());


        return new ResponseEntity<>(a,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(consumes = "application/json", value = "/delete", method=RequestMethod.POST)
    public ResponseEntity<?> updateAppointmentType(@RequestBody Id id){
        List<Appointment> appointments = appointmentService.findAll();
        Calendar cal = Calendar.getInstance();
        Date d = cal.getTime();
        AppointmentType apt = appointmentTypeService.findOneById(id.id);

        int deletable = 1;

        for(Appointment app : appointments){
            if(app.getType().equals(apt.getName()) && d.before(app.getDate())){
                deletable=0;
                break;
            }
        }

        if(deletable == 1){
            appointmentTypeService.deleteAppointmentType(id.id);
        } else {
            return new ResponseEntity<>(0,HttpStatus.OK);
        }


        return new ResponseEntity<>(1,HttpStatus.OK);
    }

    static class Id{
        public Long id;
    }
}
