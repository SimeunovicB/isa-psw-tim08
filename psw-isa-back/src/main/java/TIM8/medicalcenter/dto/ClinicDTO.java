package TIM8.medicalcenter.dto;

import TIM8.medicalcenter.model.Clinic;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClinicDTO {

    private Long id;
    private String name;
    private String address;
    private String description;

    public ClinicDTO() {
    }


    public ClinicDTO(Clinic clinic) {
        this(clinic.getId(),clinic.getName(),clinic.getAddress(),clinic.getDescription());
    }

    public ClinicDTO(Long id, String name, String address, String description) {
        super();
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
    }
}
