package TIM8.medicalcenter.dto;

import TIM8.medicalcenter.model.MedicalRecord;

public class MedicalRecordDTO {

    private Long id;
    private Double height;
    private Double weight;
    private Double diopter;
    private String alergies;
    private String bloodType;

    public MedicalRecordDTO(Long id, Double height, Double weight, Double diopter, String alergies, String bloodType) {
        this.id = id;
        this.height = height;
        this.weight = weight;
        this.diopter = diopter;
        this.alergies = alergies;
        this.bloodType = bloodType;
    }
    public MedicalRecordDTO(MedicalRecord record){
        this(record.getId(),record.getHeight(), record.getWeight(), record.getDiopter(),record.getAlergies(), record.getBloodType());
    }
    public MedicalRecordDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getDiopter() {
        return diopter;
    }

    public void setDiopter(Double diopter) {
        this.diopter = diopter;
    }

    public String getAlergies() {
        return alergies;
    }

    public void setAlergies(String alergies) {
        this.alergies = alergies;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }
}
