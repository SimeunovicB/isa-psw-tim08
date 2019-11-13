package TIM8.medicalcenter.repository;

import TIM8.medicalcenter.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface PersonRepository extends JpaRepository<Person,Long> {

    List<Person> findAll();

    Person findOneById(Long id);


    List<Person> findByFirstName(String firstName);

    @Query("select s from Person s where s.email = ?1")
    Person findOneByEmail(String mail);

    @Modifying
    @Query(
            value =
                    "insert into person(type,first_name,last_name,email,password,address,status,jmbg) " +
                               "values ('p',:first_name,:last_name,:email,:password,:address,'pending',:jmbg)",
            nativeQuery = true)
    void savePatient(@Param("address") String address, @Param("first_name") String first_name,
                    @Param("last_name") String last_name, @Param("email") String email,@Param("jmbg") String jmbg,
                    @Param("password") String password);

    @Query("SELECT p FROM Person p where p.class=?1")
    public List<Person> findByDiscriminatorValue(String discriminatorValue);

}

