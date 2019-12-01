package TIM8.medicalcenter.repository;

import TIM8.medicalcenter.dto.VacationRequestsDTO;
import TIM8.medicalcenter.model.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VacationRepository extends JpaRepository<Vacation,Long> {

    //@Query("SELECT v FROM vacation v where v.status=?1")
    //List<Vacation> findByStatus(String status);

    //@Query(value = "SELECT NEW VacationRequestsDTO(v.id,v.start_date,v.end_date,v.status,p.first_name,p.last_name) from Vacation v,Person p where p.id = v.staff_id and v.status =?1",nativeQuery = true)
    //List<VacationRequestsDTO> findByStatusWithDoctorInfo(String status);
}
