package TIM8.medicalcenter.model.Users;

import TIM8.medicalcenter.model.Clinic;
import TIM8.medicalcenter.model.Users.Person;
import TIM8.medicalcenter.model.Vacation;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("N")
public class Nurse extends Person {
    @Column(name = "worktime_start")
    private String worktimeStart;

    @Column(name="worktime_end")
    private String worktimeEnd;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Clinic clinic;

    @OneToMany(mappedBy = "staff",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    Set<Vacation> vacations = new HashSet<>();

    public String getWorktimeStart() {
        return worktimeStart;
    }

    public void setWorktimeStart(String worktimeStart) {
        this.worktimeStart = worktimeStart;
    }

    public String getWorktimeEnd() {
        return worktimeEnd;
    }

    public void setWorktimeEnd(String worktimeEnd) {
        this.worktimeEnd = worktimeEnd;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }



}
