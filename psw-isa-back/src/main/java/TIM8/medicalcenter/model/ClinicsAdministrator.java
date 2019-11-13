package TIM8.medicalcenter.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue("CCA")
public class ClinicsAdministrator extends Person {
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Clinic clinic;

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

}
