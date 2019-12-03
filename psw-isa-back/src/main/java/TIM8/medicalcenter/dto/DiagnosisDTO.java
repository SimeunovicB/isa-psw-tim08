package TIM8.medicalcenter.dto;

import TIM8.medicalcenter.model.Diagnosis;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
