package TIM8.medicalcenter.dto;

import TIM8.medicalcenter.model.users.Person;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String password;
    private String username;
    private String status;

    public PersonDTO() {

    }

    public PersonDTO(Long id, String firstName, String lastName, String address, String password, String username,String status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.password = password;
        this.username = username;
        this.status = status;
    }
    public PersonDTO(Person p){
        this(p.getId(),p.getFirstName(),p.getLastName(),p.getAddress(),p.getPassword(),p.getUsername(),p.getStatus());
    }
    @Override
    public String toString() {
        return "PersonDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
