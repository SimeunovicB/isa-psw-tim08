package TIM8.medicalcenter.model;

import TIM8.medicalcenter.model.users.Doctor;
import TIM8.medicalcenter.model.users.Patient;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Appointment {

    //region column definitions
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type",nullable = false)
    private String type;

    @Column(name = "date",nullable = false)
    private Date date;

    @Column(name = "duration",nullable = false)
    private int duration;

    @Column(name = "price",nullable = false)
    private double price;

    @Column(name = "discount")
    private int discount;

    @Column(name = "status",nullable = false)
    private String status;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Room room;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Doctor doctor;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Patient patient;
    //endregion

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
        Appointment a = (Appointment) o;
        if (a.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, a.id);
    }
    //endregion
}
