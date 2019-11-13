package TIM8.medicalcenter.dto;


import TIM8.medicalcenter.model.Patient;
import TIM8.medicalcenter.model.Person;

public class PatientDTO extends PersonDTO {
    private String jmbg;

    public PatientDTO() {
        super();
    }
    public PatientDTO(Long id, String firstName, String lastName, String address, String password, String email,String jmbg){
        super(id,firstName,lastName,address,password,email);
        this.jmbg = jmbg;
    }
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
