package TIM8.medicalcenter.dto;


import TIM8.medicalcenter.model.Users.Patient;
import TIM8.medicalcenter.model.Users.Person;

public class PatientDTO extends PersonDTO {
    private String jmbg;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    private String name;
    private String lastname;

    public PatientDTO(Patient p){
        super((Person) p);
        this.jmbg = p.getJmbg();
    }
    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }
}
