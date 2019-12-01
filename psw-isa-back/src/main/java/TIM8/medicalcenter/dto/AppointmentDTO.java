package TIM8.medicalcenter.dto;

import TIM8.medicalcenter.model.Appointment;
import TIM8.medicalcenter.model.Users.Doctor;

import java.util.Date;

public class AppointmentDTO {

    public AppointmentDTO() {

    }

    public AppointmentDTO(Appointment a) {
        this.date=a.getDate();
        this.doctor=a.getDoctor();
        this.type=a.getType();
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Date getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    private Doctor doctor;

    public AppointmentDTO(Doctor doctor, Date date, String type) {
        this.doctor = doctor;
        this.date = date;
        this.type = type;
    }

    private Date date;
    private  String type;
}
