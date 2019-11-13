package TIM8.medicalcenter.service;

import TIM8.medicalcenter.model.Patient;
import TIM8.medicalcenter.model.Person;
import TIM8.medicalcenter.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;

    public List<Person> findAll() {
        return personRepository.findAll();
    }
    public Person findOneByEmail(String email) {
        return personRepository.findOneByEmail(email);
    }
    public Person findOneById(Long id){
        return personRepository.findOneById(id);
    }
    public Person save(Person person) {
        return personRepository.save(person);
    }
    public List<Person> findByType(String type) { return personRepository.findByDiscriminatorValue(type);}
    public int updatePersonStatus(String status,Long id) {return personRepository.updateUserStatus(status,id);}
}
