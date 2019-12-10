package TIM8.medicalcenter.model;

import TIM8.medicalcenter.model.users.Patient;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class MedicalRecord {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "height",nullable = false)
    private Double height;

    @Column(name = "weight",nullable =false)
    private Double weight;

    @Column(name = "diopter")
    private Double diopter;

    @Column(name = "alergies")
    private String alergies;

    @Column(name = "blood_type",nullable = false)
    private String bloodType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;
}
