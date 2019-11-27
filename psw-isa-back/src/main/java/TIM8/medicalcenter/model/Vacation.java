package TIM8.medicalcenter.model;

import TIM8.medicalcenter.model.Users.Doctor;
import TIM8.medicalcenter.model.Users.Person;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Vacation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date",nullable = false)
    private Date startDate;

    @Column(name = "end_date",nullable = false)
    private Date endDate;

    @Column(name = "status",nullable = false)
    private String status;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Person staff;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Person getStaff() {
        return staff;
    }

    public void setStaff(Person staff) {
        this.staff = staff;
    }

    public Vacation() {
    }

}
