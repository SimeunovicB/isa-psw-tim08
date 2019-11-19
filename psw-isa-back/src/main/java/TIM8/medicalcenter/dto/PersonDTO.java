package TIM8.medicalcenter.dto;

import TIM8.medicalcenter.model.Users.Person;

public class PersonDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String password;
    private String username;

    public PersonDTO() {

    }

    public PersonDTO(Long id, String firstName, String lastName, String address, String password, String username) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.password = password;
        this.username = username;
    }
    public PersonDTO(Person p){
        this(p.getId(),p.getFirstName(),p.getLastName(),p.getAddress(),p.getPassword(),p.getUsername());
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

    public String getUsername() {
        return username;
    }
     public void setEmail(String username) {
        this.username = username;
    }
}
