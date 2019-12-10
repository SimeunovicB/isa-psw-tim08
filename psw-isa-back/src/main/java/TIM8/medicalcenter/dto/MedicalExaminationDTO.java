package TIM8.medicalcenter.dto;

import TIM8.medicalcenter.model.MedicalExaminationReport;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicalExaminationDTO {
    private Long doctor;
    private String description;
    private Long diagnosis;
    private Long medicine;


}
