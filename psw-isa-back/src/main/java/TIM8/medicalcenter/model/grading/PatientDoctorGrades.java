package TIM8.medicalcenter.model.grading;

import TIM8.medicalcenter.model.users.Doctor;
import TIM8.medicalcenter.model.users.Patient;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
public class PatientDoctorGrades {

    @EmbeddedId
    private PatientDoctorId id = new PatientDoctorId();

    @JsonManagedReference
    @ManyToOne
    @MapsId("patient_id")
    private Patient patient;

    @JsonManagedReference
    @ManyToOne
    @MapsId("doctor_id")
    private Doctor doctor;

    @Column(name = "grade")
    private double grade;


    @Embeddable
    @Getter
    @Setter
    public static class PatientDoctorId implements Serializable {

        private static final long serialVersionUID = 1L;

        private Long patient_id;
        private Long doctor_id;

        public PatientDoctorId() {

        }

        public PatientDoctorId(Long patient_id, Long doctor_id) {
            super();
            this.patient_id = patient_id;
            this.doctor_id = doctor_id;
        }

       @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result
                    + ((patient_id == null) ? 0 : patient_id.hashCode());
            result = prime * result
                    + ((doctor_id == null) ? 0 : doctor_id.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            PatientDoctorId other = (PatientDoctorId) obj;
            return Objects.equals(getDoctor_id(), other.getDoctor_id()) && Objects.equals(getPatient_id(), other.getPatient_id());
        }
    }

}

