package TIM8.medicalcenter.model.users;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@DiscriminatorValue("CCA")
@Getter
@Setter
public class ClinicsAdministrator extends Person {

}
