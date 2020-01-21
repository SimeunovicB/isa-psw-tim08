package TIM8.medicalcenter.service;

import TIM8.medicalcenter.model.Room;
import TIM8.medicalcenter.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public Room findOneByName(String name) {return roomRepository.findByName(name);}
    public List<Room> findByNameAndNumber(String name,int number) { return roomRepository.findByNameAndNumber(name,number);}
    public Room findOneById(Long id) {return roomRepository.findOneById(id);}
    public List<Room> findAll() {return roomRepository.findAll();}
    //public Room save()
}
