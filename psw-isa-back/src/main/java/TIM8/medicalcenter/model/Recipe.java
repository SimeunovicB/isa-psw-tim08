package TIM8.medicalcenter.model;

import TIM8.medicalcenter.model.users.Nurse;
import TIM8.medicalcenter.model.users.Patient;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Version
    @Column(name="version",columnDefinition = "integer DEFAULT 0",nullable = false)
    private int version;

    @Column(name = "medicine",nullable = false)
    private String medicine;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Nurse nurse;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Patient patient;






}
