package TIM8.medicalcenter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePredefDTO {
    private Long doctorId;
    private Long roomId;
    private Long typeId;
    private long cena;
    private double popust;
    private String dat;




}
