package TIM8.medicalcenter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDTO {
    private String firstName;
    private String lastName;
    private String address;
    private String jmbg;
    private String username;
    private String password;
}
