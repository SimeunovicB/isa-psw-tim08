package TIM8.medicalcenter.model.grading;

import TIM8.medicalcenter.model.Clinic;
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
public class PatientClinicGrades {
    @EmbeddedId
    private PatientClinicId id = new PatientClinicId();

    @JsonManagedReference
    @ManyToOne
    @MapsId("patient_id")
    private Patient patient;

    @JsonManagedReference
    @ManyToOne
    @MapsId("clinic_id")
    private Clinic clinic;

    @Column(name = "grade")
    private double grade;


    @Version
    private Long version = 0L;


    @Embeddable
    @Getter
    @Setter
    public static class PatientClinicId implements Serializable {

        private static final long serialVersionUID = 1L;

        private Long patient_id;
        private Long clinic_id;

        public PatientClinicId() {

        }

        public PatientClinicId(Long patient_id, Long doctor_id) {
            super();
            this.patient_id = patient_id;
            this.clinic_id = doctor_id;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result
                    + ((patient_id == null) ? 0 : patient_id.hashCode());
            result = prime * result
                    + ((clinic_id == null) ? 0 : clinic_id.hashCode());
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
            PatientClinicId other = (PatientClinicId) obj;
            return Objects.equals(getClinic_id(), other.getClinic_id()) && Objects.equals(getPatient_id(), other.getPatient_id());
        }
    }

}
