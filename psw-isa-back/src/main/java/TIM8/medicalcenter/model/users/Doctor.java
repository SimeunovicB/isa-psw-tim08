package TIM8.medicalcenter.model.users;

import TIM8.medicalcenter.model.Appointment;
import TIM8.medicalcenter.model.Clinic;
import TIM8.medicalcenter.model.MedicalExaminationReport;
import TIM8.medicalcenter.model.Vacation;
import TIM8.medicalcenter.model.grading.PatientDoctorGrades;
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
@DiscriminatorValue("D")
public class Doctor extends Person {
    @Column(name = "worktime_start")
    private String worktimeStart;

    @Column(name="worktime_end")
    private String worktimeEnd;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Clinic clinic;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    Set<Appointment> appointments = new HashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "staff",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    Set<Vacation> vacations = new HashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "doctor",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    Set<MedicalExaminationReport>  medicalExaminationReports = new HashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "doctor",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    Set<PatientDoctorGrades> grades = new HashSet<>();
}
