package TIM8.medicalcenter.dto;

import TIM8.medicalcenter.model.users.Doctor;
import TIM8.medicalcenter.model.users.Patient;
import TIM8.medicalcenter.model.users.Person;

import java.util.ArrayList;
import java.util.List;

public class DoctorDTO extends PersonDTO {
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

    public List<String> getDates() {
        return dates;
    }

    public void setDates(List<String> dates) {
        this.dates = dates;
    }

    private String name;
    private String lastname;
    private List<String> dates=new ArrayList<>();

    public DoctorDTO() {
    }

    public DoctorDTO(Doctor p){
        super((Person) p);
    }
    public DoctorDTO(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }
}
