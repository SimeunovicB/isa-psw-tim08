package TIM8.medicalcenter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateDoctorDTO {
    private String firstName;
    private String lastName;
    private String address;
    private String password;
    private String username;
    private String status;
    private Long clinic_id;
}
