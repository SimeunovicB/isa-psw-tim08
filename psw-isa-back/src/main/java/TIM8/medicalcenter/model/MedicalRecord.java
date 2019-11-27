package TIM8.medicalcenter.model;

import TIM8.medicalcenter.model.Users.Patient;
import TIM8.medicalcenter.model.Users.Person;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class MedicalRecord {

    //region column definitions
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

    //endregion

    //region Getters and setters
    public MedicalRecord() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getDiopter() {
        return diopter;
    }

    public void setDiopter(Double diopter) {
        this.diopter = diopter;
    }

    public String getAlergies() {
        return alergies;
    }

    public void setAlergies(String alergies) {
        this.alergies = alergies;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    //endregion
}
