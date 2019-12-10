package TIM8.medicalcenter.dto;

import TIM8.medicalcenter.model.Diagnosis;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DiagnosisDTO {
    private Long id;
    private String name;

    public DiagnosisDTO(Long id,String name){
        this.id = id;
        this.name = name;
    }
    public DiagnosisDTO(){

    }
    public DiagnosisDTO(Diagnosis d){
        this(d.getId(),d.getName());
    }


}
