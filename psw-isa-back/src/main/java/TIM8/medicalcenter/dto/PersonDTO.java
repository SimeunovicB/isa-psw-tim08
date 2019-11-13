package TIM8.medicalcenter.dto;

import TIM8.medicalcenter.model.Clinic;
import TIM8.medicalcenter.model.Person;

public class PersonDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String password;
    private String email;

    public PersonDTO() {

    }

    public PersonDTO(Long id, String firstName, String lastName, String address, String password, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.password = password;
        this.email = email;
    }
    public PersonDTO(Person p){
        this(p.getId(),p.getFirstName(),p.getLastName(),p.getAddress(),p.getPassword(),p.getEmail());
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
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
     public void setEmail(String email) {
        this.email = email;
    }
}
