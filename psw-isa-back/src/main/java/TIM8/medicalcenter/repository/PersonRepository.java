package TIM8.medicalcenter.repository;

import TIM8.medicalcenter.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface PersonRepository extends JpaRepository<Person,Long> {

    List<Person> findAll();

    Person findOneById(Long id);

    @Query("select s from Person s where s.email = ?1")
    Person findOneByEmail(String mail);
}
