package TIM8.medicalcenter.dto;

import TIM8.medicalcenter.model.users.Administrator;
import TIM8.medicalcenter.model.users.ClinicsAdministrator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministratorDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String password;
    private String username;
    private String status;
    private Long clinic_id;

    public AdministratorDTO() {
    }

    public AdministratorDTO(Long id, String firstName, String lastName, String address, String password, String username, String status, Long clinic_id) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.password = password;
        this.username = username;
        this.status = status;
        this.clinic_id = clinic_id;
    }
    public AdministratorDTO(Administrator a){
        this(a.getId(),a.getFirstName(),a.getLastName(),a.getAddress(),a.getPassword(),a.getUsername(),a.getStatus(),a.getId());
    }
    public AdministratorDTO(ClinicsAdministrator a){
        this(a.getId(),a.getFirstName(),a.getLastName(),a.getAddress(),a.getPassword(),a.getUsername(),a.getStatus(),a.getId());
    }
}
