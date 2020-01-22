package TIM8.medicalcenter.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentRequestDTOResponse {
    private Long doctor;
    private Long patient;
    private Date date;
    private String type;


}
