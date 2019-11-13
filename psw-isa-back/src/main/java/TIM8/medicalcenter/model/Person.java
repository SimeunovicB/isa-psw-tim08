package TIM8.medicalcenter.model;

import javax.persistence.*;
import javax.validation.constraints.Email;

import java.util.Objects;

import static javax.persistence.DiscriminatorType.STRING;
import static javax.persistence.InheritanceType.SINGLE_TABLE;

@Entity
@Inheritance(strategy=SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType=STRING)
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name",nullable = false)
    private String firstName;

    @Column(name = "last_name",nullable = false)
    private String lastName;

    @Column(name = "email",unique = true,nullable = false)
    private String email;

    @Column(name ="password",nullable = false)
    private String password;

    @Column(name = "address",nullable = false)
    private String address;

    @Column(name= "status",nullable = false)
    private String status;

    @Transient
    public String getDecriminatorValue() {
        return this.getClass().getAnnotation(DiscriminatorValue.class).value();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        Person p = (Person) o;
        if (p.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, p.id);
    }
    public Person() {

    }
}
