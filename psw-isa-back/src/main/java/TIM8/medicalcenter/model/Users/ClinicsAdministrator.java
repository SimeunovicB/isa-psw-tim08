package TIM8.medicalcenter.model.Users;

import TIM8.medicalcenter.model.Clinic;
import TIM8.medicalcenter.model.Users.Person;

import javax.persistence.*;

@Entity
@DiscriminatorValue("CCA")
public class ClinicsAdministrator extends Person {

}
