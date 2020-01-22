package TIM8.medicalcenter.repository;

import TIM8.medicalcenter.model.AppointmentRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRequestRepository extends JpaRepository<AppointmentRequest,Long> {

}
