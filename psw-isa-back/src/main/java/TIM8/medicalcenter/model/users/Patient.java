package TIM8.medicalcenter.model.users;

import TIM8.medicalcenter.model.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@DiscriminatorValue("P")
public class Patient extends Person {

    @Column(name="jmbg")
    private String jmbg;

    @ManyToMany(mappedBy = "patients")
    Set<Clinic> clinics = new HashSet<Clinic>();

    @JsonManagedReference
    @OneToOne(mappedBy = "patient")
    private MedicalRecord medicalRecord;

    @JsonBackReference
    @OneToMany(mappedBy = "patient",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    Set<MedicalExaminationReport> medicalExaminationReports = new HashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "patient",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    Set<Appointment> appointments = new HashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "patient",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    Set<Recipe> recies = new HashSet<>();
}
