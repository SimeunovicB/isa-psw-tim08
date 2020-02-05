package TIM8.medicalcenter.chrono;

import TIM8.medicalcenter.model.Appointment;
import TIM8.medicalcenter.model.AppointmentRequest;
import TIM8.medicalcenter.model.Room;
import TIM8.medicalcenter.model.users.Doctor;
import TIM8.medicalcenter.model.users.Patient;
import TIM8.medicalcenter.service.AppointmentRequestService;
import TIM8.medicalcenter.service.AppointmentService;
import TIM8.medicalcenter.service.PersonService;
import TIM8.medicalcenter.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;

import javax.persistence.Entity;
import java.util.*;


public class GenerateAppointments {

    @Autowired
    private AppointmentRequestService appointmentRequestService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PersonService personService;

    //@Scheduled(cron="0 0 0 1/1 * ? *")
    //@Scheduled(fixedRate = 1000)
    public void generateAppointments() {
        List<AppointmentRequest> requests = appointmentRequestService.findAll();


        for(AppointmentRequest ar : requests) {
            List<Room> rooms = roomService.findAll();
            List<Appointment> appointments = appointmentService.findAll();
            int flag = 0;
            Date date = Calendar.getInstance().getTime();

            Appointment a = new Appointment();
            Doctor d = (Doctor) personService.findOneById(ar.getDoctor_id());
            Patient p = (Patient) personService.findOneById(ar.getPatient_id());
            a.setPatient(p);
            a.setDoctor(d);
            a.setStatus("ACTIVE");
            a.setType(ar.getAppointment_type());
            a.setDiscount(10);
            a.setPrice(10000);
            System.out.println("prolazak");




            for(Room r : rooms) {
                HashSet<Integer> free_hours = new HashSet<>();
                for(int i=8;i<=17;i++){
                    free_hours.add(i);
                }
                Calendar cal = Calendar.getInstance();
                for(Appointment appointment : r.getAppointments()) {



                    if(date.getYear() == appointment.getDate().getYear() && date.getMonth() == date.getMonth() && date.getDay() == appointment.getDate().getDay()){
                        //TODO: POREDJENJE NIJE DOBRO
                        free_hours.remove(appointment.getDate().getHours());
                    }


                    //a.setRoom(r);

                    //a.setDate(d1);
                }
                for(int i=8;i<17;i++){
                    if(free_hours.contains(i)){
                        a.setRoom(r);
                        Calendar cal2 = Calendar.getInstance();
                        cal2.set(date.getYear(),date.getMonth(),date.getDay(),i,0,0);
                        Date date2 = date;
                        date2.setHours(i);
                        date2.setMinutes(0);
                        date2.setSeconds(0);
                        a.setDate(date2);
                        flag = 1;
                        Appointment a1 = appointmentService.save(a);

                        appointmentRequestService.delete(ar.getId());
                        break;

                    }
                }
                if(flag == 1){
                    break;
                }
            }


        }
    }



}
