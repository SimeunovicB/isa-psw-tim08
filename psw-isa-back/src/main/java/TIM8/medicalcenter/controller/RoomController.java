package TIM8.medicalcenter.controller;

import TIM8.medicalcenter.dto.FindRoomDTORequest;
import TIM8.medicalcenter.dto.FindRoomDTOResponse;
import TIM8.medicalcenter.dto.FreeAppoitmentDTOResponse;
import TIM8.medicalcenter.model.Appointment;
import TIM8.medicalcenter.model.Room;
import TIM8.medicalcenter.service.RoomService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.*;

@RestController
@RequestMapping(value = "api/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @RequestMapping(value = "/findRooms",method = RequestMethod.POST)
    public ResponseEntity<?> findRooms(@RequestBody FindRoomDTORequest request){
        List<Room> rooms = roomService.findByNameAndNumber(request.getName(),request.getNumber());
        List<FindRoomDTOResponse> res = new ArrayList<>();
        for(Room r : rooms){
            ArrayList<Date> appointments = new ArrayList<>();
            Date d = request.getDate();

            for(int i = 0; i < 10; i++){
                Date dd = new Date(d.getYear(),d.getMonth(),d.getDay()-2,8+i,0,0);
                int flag = 0;
                Set<Appointment> appointments1 = r.getAppointments();
                for(Appointment a : appointments1){
                    if(a.getDate().compareTo(dd) == 0){
                        flag = 1;
                    }
                }
                if(flag == 0){
                    appointments.add(dd);
                }
            }
            res.add(new FindRoomDTOResponse(r.getName(),r.getNumber(),appointments));
        }
        return new ResponseEntity<>(res,HttpStatus.OK);

    }




}
