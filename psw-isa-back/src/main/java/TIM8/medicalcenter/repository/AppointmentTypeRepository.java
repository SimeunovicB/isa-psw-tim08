package TIM8.medicalcenter.repository;

import TIM8.medicalcenter.model.AppointmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AppointmentTypeRepository extends JpaRepository<AppointmentType, Long> {

    List<AppointmentType> findAll();
    AppointmentType findByName(String name);
    AppointmentType findOneById(Long id);

    @Modifying
    @Transactional
    @Query("update AppointmentType at set at.name = :name where at.id = :id")
    int updateAppointmentType(@Param("name") String name, @Param("id") Long id);
}
