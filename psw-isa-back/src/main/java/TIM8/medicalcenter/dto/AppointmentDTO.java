package TIM8.medicalcenter.dto;

import TIM8.medicalcenter.model.Appointment;
import TIM8.medicalcenter.model.users.Doctor;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

import java.util.Date;

@Getter
@Setter
public class AppointmentDTO {
    private Doctor doctor;
    private Date date;
    private  String type;

    public AppointmentDTO() {

    }
    public AppointmentDTO(Appointment a) {
        this(a.getDoctor(),a.getDate(),a.getType());
    }
    public AppointmentDTO(Doctor doctor, Date date, String type) {
        this.doctor = doctor;
        this.date = date;
        this.type = type;
    }


}
