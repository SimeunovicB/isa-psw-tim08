package TIM8.medicalcenter.dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

@Getter
@Setter
public class VacationRequestsDTO {
    private Long id;
    private Date startDate;
    private String endDate;
    private String status;
    private String firstName;
    private String lastName;

    public VacationRequestsDTO(Long id, Date startDate, String endDate, String status, String firstName, String lastName) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public VacationRequestsDTO() {
    }
}


