package TIM8.medicalcenter.dto;

import TIM8.medicalcenter.model.Clinic;
import TIM8.medicalcenter.model.Person;

public class PersonDTO {

    private String password;
    private String email;

    public PersonDTO(String email, String password) {
        this.password = password;
        this.email = email;
    }

    public PersonDTO(Person person) {
        this(person.getEmail(),person.getPassword());
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
