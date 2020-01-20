package TIM8.medicalcenter.model;

import TIM8.medicalcenter.model.users.Nurse;
import TIM8.medicalcenter.model.users.Patient;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "medicine",nullable = false)
    private String medicine;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Nurse nurse;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Patient patient;






}
