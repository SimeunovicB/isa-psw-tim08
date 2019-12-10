package TIM8.medicalcenter.dto;


import TIM8.medicalcenter.model.users.Patient;
import TIM8.medicalcenter.model.users.Person;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PatientDTO extends PersonDTO {
    private String jmbg;
    private String name;
    private String lastname;

    public PatientDTO() {
    }

    public PatientDTO(String jmbg, String name, String lastname) {
        this.jmbg = jmbg;
        this.name = name;
        this.lastname = lastname;
    }

    public PatientDTO(Long id, String firstName, String lastName, String address, String password, String username, String status, String jmbg, String name, String lastname) {
        super(id, firstName, lastName, address, password, username, status);
        this.jmbg = jmbg;
        this.name = name;
        this.lastname = lastname;
    }

    public PatientDTO(Patient p){
        super((Person) p);
        this.jmbg = p.getJmbg();
    }

}
