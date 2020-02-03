package TIM8.medicalcenter.model;

import TIM8.medicalcenter.model.users.Doctor;
import TIM8.medicalcenter.model.users.Patient;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class MedicalExaminationReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Version
    @Column(name="version",columnDefinition = "integer DEFAULT 0",nullable = false)
    private int version;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Diagnosis diagnosis;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Medicine medicine;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Doctor doctor;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Patient patient;





}
