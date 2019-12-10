package TIM8.medicalcenter.dto;

import TIM8.medicalcenter.model.MedicalRecord;
import TIM8.medicalcenter.model.users.Patient;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MedicalRecordDTO {

    private Long id;
    private Double height;
    private Double weight;
    private Double diopter;
    private String alergies;
    private String bloodType;

    private String firstName;
    private String lastName;
    private String jmbg;

    public MedicalRecordDTO(Long id, Double height, Double weight, Double diopter, String alergies, String bloodType,String firstName,String lastName,String jmbg) {
        this.id = id;
        this.height = height;
        this.weight = weight;
        this.diopter = diopter;
        this.alergies = alergies;
        this.bloodType = bloodType;
        this.firstName =firstName;
        this.lastName = lastName;
        this.jmbg = jmbg;
    }
    public MedicalRecordDTO(MedicalRecord record, Patient p){
        this(record.getId(),record.getHeight(), record.getWeight(), record.getDiopter(),record.getAlergies(), record.getBloodType(),p.getFirstName(),p.getLastName(),p.getJmbg());
    }
    public MedicalRecordDTO() {

    }
}
