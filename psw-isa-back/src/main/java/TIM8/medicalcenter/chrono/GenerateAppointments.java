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
        List<Room> rooms = roomService.findAll();
        List<Appointment> appointments = appointmentService.findAll();

        for(AppointmentRequest ar : requests) {
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

                    cal.set(date.getYear(),date.getMonth(),date.getDay(),0,0,0);

                    if(cal.getTime().getYear() == appointment.getDate().getYear() && cal.getTime().getMonth() == appointment.getDate().getMonth() && cal.getTime().getYear() == appointment.getDate().getDay()){

                        free_hours.remove(appointment.getDate().getHours());
                    }


                    //a.setRoom(r);

                    //a.setDate(d1);
                }
                for(int i=8;i<17;i++){
                    if(free_hours.contains(i)){
                        a.setRoom(r);
                        Calendar cal2 = Calendar.getInstance();
                        cal.set(date.getYear(),date.getMonth(),date.getDay(),i,0,0);
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
