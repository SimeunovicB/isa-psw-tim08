package TIM8.medicalcenter.dto;

import TIM8.medicalcenter.model.Room;

public class RoomDTO {
    private Long id;
    private String name;

    public RoomDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public RoomDTO() {
    }

    public RoomDTO(Room r) { this(r.getId(), r.getName()); }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
