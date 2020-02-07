package TIM8.medicalcenter.service;

import TIM8.medicalcenter.dto.CreatePredefDTO;
import TIM8.medicalcenter.dto.Request.PredefAppointmentDTORequest;
import TIM8.medicalcenter.model.Appointment;
import TIM8.medicalcenter.model.Room;
import TIM8.medicalcenter.model.users.Doctor;
import TIM8.medicalcenter.model.users.Patient;
import TIM8.medicalcenter.model.users.Person;
import TIM8.medicalcenter.repository.AppointmentRepository;
import TIM8.medicalcenter.repository.AppointmentTypeRepository;
import TIM8.medicalcenter.repository.PersonRepository;
import TIM8.medicalcenter.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class AppointmentService  {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private AppointmentTypeRepository appointmentTypeRepository;


    public List<Appointment> findAll(){return appointmentRepository.findAll();}
    public List<Appointment> findAdminAppointments(Long id) {return appointmentRepository.findAdminAppointments(id);}
    public List<Appointment> findAppointments(String type){return appointmentRepository.findAppointments(type);}
    public Appointment save(Appointment a) {  return appointmentRepository.save(a); }
    public List<Appointment> findAllByDoctorId(Long id){
        Doctor d = (Doctor) personRepository.findOneById(id);
        return appointmentRepository.findAllByDoctor(d);
    }

    /**
     * Mora se spreciti mogucnost da dva razilicita pacijenta rezervisu predefinisani pregled u isto vreme
     * @param a
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Appointment reserve(PredefAppointmentDTORequest a){
        Appointment a1 = appointmentRepository.findOneById(a.getAppointmentId());
        Patient p = (Patient) personRepository.findOneById(a.getPatientId());
        a1.setPatient(p);
        Appointment a2  = appointmentRepository.save(a1);
        return a2;
    }

    /**
     * Mora se enkapsulirati u transakciju zato sto je moguce da dva administratora odluce da u isto vreme naprave predefinisani pregled sa istim doktorom
     * Takodje ovo moze dovesti do situacije da pacijent koji preuzima predefinisane preglede ne dobije neki predefinisan pregled - Unrepeatable Read nivo zakljucavanja 3
     * @param dto
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createPredef(CreatePredefDTO dto){
        Appointment a = new Appointment();
        Doctor d  = (Doctor) personRepository.findOneById(dto.getDoctorId());
        Room r = roomRepository.findOneById(dto.getRoomId());
        String at = appointmentTypeRepository.findOneById(dto.getTypeId()).getName();
        a.setDoctor(d);
        a.setRoom(r);
        a.setType(at);
        a.setPrice(dto.getCena());
        a.setDiscount((int) dto.getPopust());
        String dat = dto.getDat();
        int year = Integer.parseInt(dat.split("-")[0]);
        int month = Integer.parseInt(dat.split("-")[1]);
        int day = Integer.parseInt(dat.split("-")[2]);
        int h = Integer.parseInt(dat.split("-")[3]);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR, h);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        Date d1 = cal.getTime();
        a.setDate(d1);
        appointmentRepository.save(a);
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void cancle(Person p,Long id){
        Appointment a = appointmentRepository.findOneById(id);
        if(p instanceof Patient){
            a.setPatient(null);
            appointmentRepository.save(a);
        }else{
            appointmentRepository.deleteAppointment(id);
        }
    }
}
