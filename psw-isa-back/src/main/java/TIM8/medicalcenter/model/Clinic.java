package TIM8.medicalcenter.model;

import TIM8.medicalcenter.model.users.Administrator;
import TIM8.medicalcenter.model.users.Doctor;
import TIM8.medicalcenter.model.users.Nurse;
import TIM8.medicalcenter.model.users.Patient;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
public class Clinic {
    //region column definitions
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false,unique = true)
    private String name;

    @Column(name = "address",nullable = false)
    private String address;

    @Column(name = "description")
    private String description;

    @JsonBackReference
    @OneToMany(mappedBy = "clinic",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    Set<Doctor> doctors = new HashSet<Doctor>();

    @JsonBackReference
    @OneToMany(mappedBy = "clinic",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    Set<Nurse> nurses = new HashSet<Nurse>();

    @JsonBackReference
    @OneToMany(mappedBy = "clinic",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    Set<Room> rooms = new HashSet<Room>();

    @JsonBackReference
    @OneToMany(mappedBy = "clinic",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    Set<Administrator> administrators = new HashSet<Administrator>();

    @JsonBackReference
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "clinic_patient",
            joinColumns = @JoinColumn(name = "clinic_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id",referencedColumnName = "id")
    )
    private Set<Patient> patients = new HashSet<Patient>();
    //endregion

    public Clinic() {
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Clinic c = (Clinic) o;
        if (c.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, c.id);
    }
}
