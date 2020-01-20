package TIM8.medicalcenter.repository;

import TIM8.medicalcenter.model.Recipe;
import TIM8.medicalcenter.model.users.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public interface RecipeRepository extends JpaRepository<Recipe,Long> {

    @Modifying
    @Transactional
    @Query("update Recipe r set r.nurse = :nurse  where r.id = :id")
    int updateUserStatus(@Param("nurse") Nurse nurse, @Param("id") Long id);
}
