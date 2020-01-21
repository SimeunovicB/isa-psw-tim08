package TIM8.medicalcenter.repository;

import TIM8.medicalcenter.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findAll();
    Room findByName(String name);
    Room findOneById(Long id);

    @Query("SELECT r from Room r where r.number=:number or :number = null and r.name=:name or :name = null")
    List<Room> findByNameAndNumber(@Param("name") String name, @Param("number") int number);

}
