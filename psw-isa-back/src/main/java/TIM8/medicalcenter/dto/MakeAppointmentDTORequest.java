package TIM8.medicalcenter.dto;

import TIM8.medicalcenter.model.users.Patient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MakeAppointmentDTORequest {
    private String type;
    private Date date;
    private double price;
    private int discount;
    private Long room;
    private Long doctor;
    private Long patient;
    private Long appointmentRequestId;






}
