package TIM8.medicalcenter.service;

import TIM8.medicalcenter.dto.RoomDTO;
import TIM8.medicalcenter.model.Room;
import TIM8.medicalcenter.repository.ClinicRepository;
import TIM8.medicalcenter.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ClinicRepository clinicRepository;

    public Room findOneByName(String name) {return roomRepository.findByName(name);}
    public List<Room> findByNameAndNumber(String name,int number) { return roomRepository.findByNameAndNumber(name,number);}
    public Room findOneById(Long id) {return roomRepository.findOneById(id);}
    public List<Room> findAll() {return roomRepository.findAll();}
    public List<Room> findAdminRooms(Long id) {
        return roomRepository.findAdminRooms(id);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int updateRoom(String name, int number, Long id) {
        Room room = roomRepository.findOneById(id);
        room.setName(name);
        room.setNumber(number);
        roomRepository.save(room);
        return 1;
    }

    
    //public void deleteRoom(Long id) {roomRepository.delete(roomRepository.findOneById(id));}
    @Transactional
    public void deleteRoom(Long id) {roomRepository.deleteRoom(id);}


    public Room save(RoomDTO roomDTO) {
        Room r = new Room();
        r.setName(roomDTO.getName());
        r.setNumber(roomDTO.getNumber());
        r.setClinic(clinicRepository.findOneById(roomDTO.getId()));

        return roomRepository.save(r);
    }
}
