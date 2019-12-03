package TIM8.medicalcenter.dto;

import TIM8.medicalcenter.model.Medicine;

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
