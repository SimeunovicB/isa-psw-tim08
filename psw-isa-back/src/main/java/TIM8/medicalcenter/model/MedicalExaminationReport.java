package TIM8.medicalcenter.model;

import TIM8.medicalcenter.model.users.Doctor;
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

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Diagnosis diagnosis;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Medicine medicine;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Doctor doctor;





}
