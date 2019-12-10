package TIM8.medicalcenter.dto;

import TIM8.medicalcenter.model.Medicine;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MedicineDTO {
    private Long id;
    private String name;

    public MedicineDTO(Long id,String name){
        this.id = id;
        this.name = name;
    }
    public MedicineDTO(){

    }
    public MedicineDTO(Medicine m){
        this(m.getId(),m.getName());
    }

}
