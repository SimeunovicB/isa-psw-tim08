package TIM8.medicalcenter.dto;

import TIM8.medicalcenter.model.Clinic;

public class ClinicDTO {

    private Long id;
    private String name;
    private String address;
    private String description;


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

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }
}
