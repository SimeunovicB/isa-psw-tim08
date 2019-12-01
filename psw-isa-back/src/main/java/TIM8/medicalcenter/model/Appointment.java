package TIM8.medicalcenter.model;

import TIM8.medicalcenter.model.Users.Doctor;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
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

    public Doctor getDoctor() {
        return doctor;
    }

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Doctor doctor;
    //endregion

    //region geters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
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
        Appointment a = (Appointment) o;
        if (a.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, a.id);
    }
    //endregion
}
