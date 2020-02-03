package TIM8.medicalcenter.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
public class AppointmentType {

    //region column definitions
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false,unique = true)
    private String name;
    //endregion

    @Version
    @Column(name="version",columnDefinition = "integer DEFAULT 0",nullable = false)
    private int version;


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
