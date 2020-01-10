package TIM8.medicalcenter.repository;

import TIM8.medicalcenter.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findAll();
    Room findByName(String name);
    Room findOneById(Long id);

}
