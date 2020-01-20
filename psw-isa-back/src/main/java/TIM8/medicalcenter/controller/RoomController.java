package TIM8.medicalcenter.controller;

import TIM8.medicalcenter.model.Room;
import TIM8.medicalcenter.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @RequestMapping(value="/findroom",method = RequestMethod.GET)
    public ResponseEntity<?> findRoom(@RequestParam String name){
        Room room;
        room = roomService.findOneByName(name);

        return new ResponseEntity<>(room, HttpStatus.OK);
    }
}
