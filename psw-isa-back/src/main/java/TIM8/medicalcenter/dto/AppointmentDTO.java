package TIM8.medicalcenter.dto;

import TIM8.medicalcenter.model.Appointment;
import TIM8.medicalcenter.model.users.Doctor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

import java.util.Date;

@Getter
@Setter
public class AppointmentDTO {

    private Long appointmentId;
    private Doctor doctor;
    private Date date;
    private String type;
    private Long patientId;
    private double price;
    private long discount;
    private String room;


    public AppointmentDTO() {

    }
    public AppointmentDTO(Appointment a) {
        this(a.getId(),a.getDoctor(),a.getDate(),a.getType(),a.getPatient().getId(),a.getPrice(),a.getDiscount(),a.getRoom().getName());
    }
    public AppointmentDTO(Long appointmentId,Doctor doctor, Date date, String type,Long patientId,double price,long discount,String room) {
        this.appointmentId = appointmentId;
        this.doctor = doctor;
        this.date = date;
        this.type = type;
        this.patientId = patientId;
        this.price = price;
        this.discount = discount;
        this.room = room;
    }


}
