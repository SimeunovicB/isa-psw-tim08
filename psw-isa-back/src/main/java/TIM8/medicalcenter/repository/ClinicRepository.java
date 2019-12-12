package TIM8.medicalcenter.repository;

import TIM8.medicalcenter.model.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ClinicRepository extends JpaRepository<Clinic,Long> {
    List<Clinic> findAll();

    Clinic findOneById(Long id);
    Clinic findOneByName(String name);

    @Modifying
    @Transactional
    @Query("update Clinic c set c.name = :name,c.address = :address,c.description = :description where c.id = :id")
    int updateClinic(@Param("name") String name, @Param("address") String address, @Param("description") String description, @Param("id") Long id);
}
