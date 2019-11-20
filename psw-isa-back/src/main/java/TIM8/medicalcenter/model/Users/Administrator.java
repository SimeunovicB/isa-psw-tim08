package TIM8.medicalcenter.model.Users;

import TIM8.medicalcenter.model.Clinic;

import javax.persistence.*;

@Entity
@DiscriminatorValue("A")
public class Administrator extends Person {

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Clinic clinic;

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

}
