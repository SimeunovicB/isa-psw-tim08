package TIM8.medicalcenter.model.users;

import TIM8.medicalcenter.model.Clinic;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@DiscriminatorValue("A")
public class Administrator extends Person {

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Clinic clinic;

}
