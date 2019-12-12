package TIM8.medicalcenter.repository;

import TIM8.medicalcenter.model.AppointmentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentTypeRepository extends JpaRepository<AppointmentType, Long> {

    List<AppointmentType> findAll();
    AppointmentType findByName(String name);
}
