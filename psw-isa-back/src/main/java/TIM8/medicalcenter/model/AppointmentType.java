package TIM8.medicalcenter.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class AppointmentType {

    //region column definitions
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false,unique = true)
    private String name;
    //endregion

    //region geters and setters
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
    //endregion

    public AppointmentType() {
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AppointmentType m = (AppointmentType) o;
        if (m.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, m.id);
    }
}
