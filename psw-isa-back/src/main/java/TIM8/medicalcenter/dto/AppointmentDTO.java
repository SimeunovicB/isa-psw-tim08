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

    private Doctor doctor;
    private Date date;
    private String type;
    private Long patientId;


    public AppointmentDTO() {

    }
    public AppointmentDTO(Appointment a) {
        this(a.getDoctor(),a.getDate(),a.getType(),a.getPatient().getId());
    }
    public AppointmentDTO(Doctor doctor, Date date, String type,Long patientId) {
        this.doctor = doctor;
        this.date = date;
        this.type = type;
        this.patientId = patientId;
    }


}
