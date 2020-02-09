package TIM8.medicalcenter.controller;

import TIM8.medicalcenter.dto.FindRoomDTORequest;
import TIM8.medicalcenter.dto.FindRoomDTOResponse;
import TIM8.medicalcenter.dto.RoomDTO;
import TIM8.medicalcenter.model.Appointment;
import TIM8.medicalcenter.model.Room;
import TIM8.medicalcenter.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping(value = "api/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    /**
     * Funkcija kojom administrator nakon odabira pregleda za koji zeli da izvrsi pravljenje pregleda,dobija sve termine
     * koji su slobodni za odabrani termin
     * @param request
     * @return
     */
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/findRooms",method = RequestMethod.POST)
    public ResponseEntity<?> findRooms(@RequestBody FindRoomDTORequest request){
        List<Room> rooms = roomService.findByNameAndNumber(request.getName(),request.getNumber());
        List<FindRoomDTOResponse> res = new ArrayList<>();
        for(Room r : rooms){
            ArrayList<Date> appointments = new ArrayList<>();
            String dat = request.getDate();
            int year = Integer.parseInt(dat.split("-")[0]);
            int month = Integer.parseInt(dat.split("-")[1]);
            int day = Integer.parseInt(dat.split("-")[2]);
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, month-1);
            cal.set(Calendar.DAY_OF_MONTH, day);
            //Date d = cal.getTime();

            for(int i = 0; i < 10; i++){
                //Date dd = new Date(d.getYear(),d.getMonth(),d.getDay(),8+i,0,0);
                Date dd = cal.getTime();
                dd.setHours(8+i);
                dd.setMinutes(0);
                dd.setSeconds(0);
                //int ddy = dd.getYear();
                //int ddm = dd.getMonth();
                //int ddd = dd.getDay();
                //int ddh = dd.getHours();
                int flag = 0;
                Set<Appointment> appointments1 = r.getAppointments();
                for(Appointment a : appointments1){
                    String s = a.getDate().toString();
                    int ay = Integer.parseInt(s.split("-")[0]);
                    int am = Integer.parseInt(s.split("-")[1]);
                    String bb = s.split("-")[2].substring(0,2);
                    int ad = Integer.parseInt(s.split("-")[2].substring(0,2));
                    int ah = Integer.parseInt(s.split(" ")[1].substring(0,2));
                    if(ay == year && am == month && ad == day && ah == 8+i){
                        flag = 1;
                    }
                }
                if(flag == 0){
                    appointments.add(dd);
                }
            }
            res.add(new FindRoomDTOResponse(r.getId(), r.getName(), r.getNumber(),appointments));
        }
        return new ResponseEntity<>(res,HttpStatus.OK);

    }

    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public ResponseEntity<?> findAllRooms() {
        List<Room> rooms = roomService.findAll();
        List<FindRoomDTOResponse> response = new ArrayList<>();
        for(Room r : rooms){
            response.add(new FindRoomDTOResponse(r.getId(),r.getName(),r.getNumber(),null));
        }
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }

    /**
     * Funkcija kojom administrator dobija sobe koje moze da menja
     * @param id
     * @return
     */
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/getAdminRooms",method = RequestMethod.POST)
    public ResponseEntity<?> getAdminRooms(@RequestBody Id id) {
        List<Room> rooms = roomService.findAdminRooms(id.id);
        List<FindRoomDTOResponse> response = new ArrayList<>();
        for(Room r : rooms){
            response.add(new FindRoomDTOResponse(r.getId(),r.getName(),r.getNumber(),null));
        }
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }

    /**
     * Funkcija kojom administrator vrsi menjanje sobe
     * @param room
     * @return
     */
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<?> updateRoom(@RequestBody RoomDTO room) {
        int a = roomService.updateRoom(room.getName(), room.getNumber(), room.getId());

        return new ResponseEntity<>(a, HttpStatus.OK);
    }

    /**
     * Funkcija kojom administrator kreira novu sobu
     * @param room
     * @return
     */
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createRoom(@RequestBody RoomDTO room) {
        Room r = roomService.save(room);

        return new ResponseEntity<>(r, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/firstDate", method = RequestMethod.POST)
    public ResponseEntity<?> firstDate(@RequestBody Id id) {
        Room r = roomService.findOneById(id.id);

        int add = 1;

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, add);
        Boolean b = true;
        Date d = cal.getTime();
        d.setMinutes(0);
        d.setSeconds(0);

        while(b) {
            for (int i = 0; i < 10; i++) {
                d.setHours(8+i);
                boolean nasao = false;
                for (Appointment app : r.getAppointments()) {
                    String s = d.toString();
                    int ay = d.getYear()+1900;
                    int am = d.getMonth()+1;
                    int ad = Integer.parseInt(s.split(" ")[2]);

                    String s2 = app.getDate().toString();
                    int ay2 = Integer.parseInt(s2.split("-")[0]);
                    int am2 = Integer.parseInt(s2.split("-")[1]);
                    String bb2 = s2.split("-")[2].substring(0,2);
                    int ad2 = Integer.parseInt(s2.split("-")[2].substring(0,2));
                    int ah2 = Integer.parseInt(s2.split(" ")[1].substring(0,2));
                    if(ay == ay2 && am == am2 && ad == ad2 && ah2 == 8+i){
                        nasao = true;
                        break;
                    }
                }
                if(!nasao) {
                    b = false;
                    break;
                }
            }
            if(b==false)
                break;
            Calendar cal2 = Calendar.getInstance();
            cal2.add(Calendar.DATE, ++add);
            d = cal2.getTime();
        }
        Povratna p = new Povratna();
        p.datum = d.toString();

        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    static class Povratna {
        public String datum;
    }


    static class Id {
        public Long id;
    }
}
