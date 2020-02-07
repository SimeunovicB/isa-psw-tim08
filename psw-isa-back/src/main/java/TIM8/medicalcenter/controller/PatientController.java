package TIM8.medicalcenter.controller;

import TIM8.medicalcenter.dto.DoctorDTO;
import TIM8.medicalcenter.dto.PatientDTO;
import TIM8.medicalcenter.dto.SearchDoctorsAppointmentDTO;
import TIM8.medicalcenter.model.Appointment;
import TIM8.medicalcenter.model.users.Doctor;
import TIM8.medicalcenter.model.users.Patient;
import TIM8.medicalcenter.model.users.Person;
import TIM8.medicalcenter.service.AppointmentService;
import TIM8.medicalcenter.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "api/patient")
public class PatientController {

    @Autowired
    private PersonService personService;
    @Autowired
    private AppointmentService appointmentService;


   
    @GetMapping
    public ResponseEntity<List<PatientDTO>> getPendingPatients() {
        List<Person> patientList = personService.findByType("P");
        List<PatientDTO> patients = new ArrayList<>();
        for(Person p : patientList){
            if(p.getStatus().equalsIgnoreCase("PENDING")){
                patients.add(new PatientDTO((Patient) p));
            }
        }
        return new ResponseEntity<>(patients,HttpStatus.OK);
    }

    @GetMapping(value = "/getAllPatients")
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        List<Person> patientList = personService.findByType("P");
        List<PatientDTO> patients = new ArrayList<>();
        for(Person p : patientList){

            patients.add(new PatientDTO((Patient) p));
        }
        return new ResponseEntity<>(patients,HttpStatus.OK);
    }

    @RequestMapping(value="/findPatients",method = RequestMethod.GET)
    public ResponseEntity<?> findPatient(@RequestParam String name, @RequestParam String lastname,@RequestParam String jmbg){
        List<Patient> patientList = personService.findPatients();
        List<PatientDTO> patients = new ArrayList<>();
        for(Patient p : patientList){
            if(!p.getJmbg().equals(jmbg)&&!jmbg.equals(""))continue;
            if(!p.getLastName().equals(lastname)&&!lastname.equals(""))continue;
            if(!p.getFirstName().equals(name)&&!name.equals(""))continue;
            patients.add(new PatientDTO(p));
        }
        return new ResponseEntity<>(patients,HttpStatus.OK);
    }

    @RequestMapping(value="/findDoctors",method = RequestMethod.GET)
    public ResponseEntity<?> findDoctor(@RequestParam String name, @RequestParam String lastname,@RequestParam String ocena){
        List<Doctor> doctorList = personService.findDoctors();
        List<Appointment> apps = appointmentService.findAll();
        List<DoctorDTO> doctors = new ArrayList<>();
        for(Doctor p : doctorList){
            if(!p.getLastName().equals(lastname)&&!lastname.equals(""))continue;
            if(!p.getFirstName().equals(name)&&!name.equals(""))continue;
            DoctorDTO newDoc = new DoctorDTO(p);
            for (Appointment a:apps) {
                System.out.println(newDoc.getFirstName()+" "+a.getDoctor().getFirstName());
                if(a.getDoctor().getFirstName().equals(newDoc.getFirstName()) && a.getPatient() == null){
                    SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String strDate = formatter.format(a.getDate());
                    newDoc.getDates().add(new SearchDoctorsAppointmentDTO(strDate,a.getId()));
                }
            }
            doctors.add(newDoc);
        }
        return new ResponseEntity<>(doctors,HttpStatus.OK);
    }

}
