package TIM8.medicalcenter.dto;

import TIM8.medicalcenter.model.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FindRoomDTORequest {
    private Long id;
    private String name;
    private int number;
    private Date date;

}
