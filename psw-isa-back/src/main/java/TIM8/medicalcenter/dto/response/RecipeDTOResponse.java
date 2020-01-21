package TIM8.medicalcenter.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDTOResponse {
    String patient;
    String nurse;
    String medicine;
    Long id;
}
