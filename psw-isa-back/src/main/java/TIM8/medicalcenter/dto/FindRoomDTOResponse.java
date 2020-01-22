package TIM8.medicalcenter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FindRoomDTOResponse {
    Long id;
    String name;
    int number;
    ArrayList<Date> dates;
}
