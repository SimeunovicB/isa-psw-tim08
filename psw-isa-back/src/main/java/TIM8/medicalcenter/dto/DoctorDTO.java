package TIM8.medicalcenter.dto;

import TIM8.medicalcenter.model.users.Doctor;
import TIM8.medicalcenter.model.users.Person;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DoctorDTO extends PersonDTO {
    private String name;
    private String lastname;
    private List<SearchDoctorsAppointmentDTO> dates=new ArrayList<>();
    private Long clinic;

    public DoctorDTO() {
    }

    public DoctorDTO(Doctor p){
        super((Person) p);
    }
    public DoctorDTO(String name, String lastname, Long clinic) {
        this.name = name;
        this.lastname = lastname;
        this.clinic = clinic;
    }
}
