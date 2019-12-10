package TIM8.medicalcenter.repository;

import TIM8.medicalcenter.model.users.Patient;
import TIM8.medicalcenter.model.users.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

public interface PersonRepository extends JpaRepository<Person,Long> {

    List<Person> findAll();

    Person findOneById(Long id);
    Person findOneByUsername(String username);

    List<Person> findByFirstName(String firstName);

    @Query("SELECT p FROM Person p where p.class=?1")
    List<Person> findByDiscriminatorValue(String discriminatorValue);

    @Query("SELECT p FROM Person p where p.class='P'")
    List<Patient> findPatients();


    @Modifying
    @Transactional
    @Query("update Person p set p.status = :status where p.id = :id")
    int updateUserStatus(@Param("status") String status,@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("update Person p set p.password = :password where p.id = :id")
    int updatePassword(@Param("password") String password,@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("update Person p set p.firstName = :firstName , p.lastName = :lastName,p.address = :address where p.id = :id")
    int updateUser(@Param("firstName") String firstName,@Param("lastName") String lastName,@Param("address") String address,@Param("id") Long id);
}

