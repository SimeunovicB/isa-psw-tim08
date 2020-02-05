package TIM8.medicalcenter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchDoctorsAppointmentDTO {
    private String date;
    private Long appointmentId;
}
