package TIM8.medicalcenter.repository;

import TIM8.medicalcenter.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

public interface PersonRepository extends JpaRepository<Person,Long> {

    List<Person> findAll();

    Person findOneById(Long id);


    List<Person> findByFirstName(String firstName);

    @Query("select s from Person s where s.email = ?1")
    Person findOneByEmail(String mail);

    @Query("SELECT p FROM Person p where p.class=?1")
    List<Person> findByDiscriminatorValue(String discriminatorValue);

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

