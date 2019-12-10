package TIM8.medicalcenter.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
public class Medicine {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false,unique = true)
    private String name;

    @OneToMany(mappedBy = "medicine",fetch = FetchType.LAZY)
    private Set<MedicalExaminationReport> medicalExaminationReports = new HashSet<>();



    public Medicine() {
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
        Medicine m = (Medicine) o;
        if (m.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, m.id);
    }
}
