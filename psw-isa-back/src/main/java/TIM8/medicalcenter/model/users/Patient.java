package TIM8.medicalcenter.model.users;

import TIM8.medicalcenter.model.Clinic;
import TIM8.medicalcenter.model.MedicalRecord;
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

    @OneToOne(mappedBy = "patient")
    private MedicalRecord medicalRecord;

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public Set<Clinic> getClinics() {
        return clinics;
    }

    public void setClinics(Set<Clinic> clinics) {
        this.clinics = clinics;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }
}
