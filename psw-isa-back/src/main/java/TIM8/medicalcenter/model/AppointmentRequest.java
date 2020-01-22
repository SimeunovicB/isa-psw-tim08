package TIM8.medicalcenter.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class AppointmentRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false,unique = true)
    private String name;

    @Column(name = "doctor_id",nullable = false)
    private Long doctor_id;

    @Column(name = "patient_id",nullable = false)
    private Long patient_id;

    @Column(name = "date",nullable = false)
    private Date date;

    @Column(name = "appointment_type",nullable = false)
    private Long appointment_type;


}