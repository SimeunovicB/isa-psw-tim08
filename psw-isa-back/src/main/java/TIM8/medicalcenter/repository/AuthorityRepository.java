package TIM8.medicalcenter.repository;

import TIM8.medicalcenter.model.Security.Authority;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Authority findByName(String name);
}
