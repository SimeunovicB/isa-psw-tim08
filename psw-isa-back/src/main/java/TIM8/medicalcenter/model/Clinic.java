package TIM8.medicalcenter.model;

import TIM8.medicalcenter.model.Users.Administrator;
import TIM8.medicalcenter.model.Users.Doctor;
import TIM8.medicalcenter.model.Users.Nurse;
import TIM8.medicalcenter.model.Users.Patient;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false,unique = true)
    private String name;

    @Column(name = "address",nullable = false)
    private String address;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "clinic",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    Set<Doctor> doctors = new HashSet<Doctor>();

    @OneToMany(mappedBy = "clinic",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    Set<Nurse> nurses = new HashSet<Nurse>();

    @OneToMany(mappedBy = "clinic",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    Set<Room> rooms = new HashSet<Room>();

    @OneToMany(mappedBy = "clinic",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    Set<Administrator> administrators = new HashSet<Administrator>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "clinic_patient",
            joinColumns = @JoinColumn(name = "clinic_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id",referencedColumnName = "id")
    )
    private Set<Patient> patients = new HashSet<Patient>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<Doctor> doctors) {
        this.doctors = doctors;
    }

    public Set<Nurse> getNurses() {
        return nurses;
    }

    public void setNurses(Set<Nurse> nurses) {
        this.nurses = nurses;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public Set<Administrator> getAdministrators() {
        return administrators;
    }

    public void setAdministrators(Set<Administrator> administrators) {
        this.administrators = administrators;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }

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
