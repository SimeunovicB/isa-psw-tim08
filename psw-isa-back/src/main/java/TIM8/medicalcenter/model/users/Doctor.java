package TIM8.medicalcenter.model.users;

import TIM8.medicalcenter.model.Appointment;
import TIM8.medicalcenter.model.Clinic;
import TIM8.medicalcenter.model.MedicalExaminationReport;
import TIM8.medicalcenter.model.Vacation;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@DiscriminatorValue("D")
public class Doctor extends Person {
    @Column(name = "worktime_start")
    private String worktimeStart;

    @Column(name="worktime_end")
    private String worktimeEnd;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Clinic clinic;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    Set<Appointment> appointments = new HashSet<>();

    @OneToMany(mappedBy = "staff",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    Set<Vacation> vacations = new HashSet<>();

    @OneToMany(mappedBy = "doctor",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    Set<MedicalExaminationReport>  medicalExaminationReports = new HashSet<>();
}
