package TIM8.medicalcenter.service;

import TIM8.medicalcenter.model.Security.Authority;
import TIM8.medicalcenter.model.Users.Person;
import TIM8.medicalcenter.repository.PersonRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService implements UserDetailsService {

    protected final Log LOGGER = LogFactory.getLog(getClass());

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityService authorityService;

    @Autowired AuthenticationManager authenticationManager;

    public List<Person> findAll() { return personRepository.findAll(); }
    public Person findOneByUsername(String username) { return personRepository.findOneByUsername(username); }
    public Person findOneById(Long id){ return personRepository.findOneById(id);}
    public List<Person> findByType(String type) { return personRepository.findByDiscriminatorValue(type);}
    public int updatePersonStatus(String status,Long id) {return personRepository.updateUserStatus(status,id);}
    public int updatePassword(String password,Long id) {return personRepository.updatePassword(password,id);}
    public int updateUser(String firstName,String lastName,String address,long id) { return personRepository.updateUser(firstName,lastName,address,id); }
    // Funkcija koja na osnovu username-a iz baze vraca objekat User-a
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.findOneByUsername(username);
        if (person == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return person;
        }
    }
    public Person save(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setEnabled(true);
        person.setStatus("PENDING");
        List<Authority> auth =authorityService.findByname("ROLE_PATIENT");
        person.setAuthorities(auth);

        return personRepository.save(person);

    }
    public void changePassword(String oldPassword, String newPassword) {

        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();

        if (authenticationManager != null) {
            LOGGER.debug("Re-authenticating user '" + username + "' for password change request.");

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, oldPassword));
        } else {
            LOGGER.debug("No authentication manager set. can't change Password!");

            return;
        }

        LOGGER.debug("Changing password for user '" + username + "'");

        Person user = (Person) loadUserByUsername(username);

        // pre nego sto u bazu upisemo novu lozinku, potrebno ju je hesirati
        // ne zelimo da u bazi cuvamo lozinke u plain text formatu
        user.setPassword(passwordEncoder.encode(newPassword));
        personRepository.save(user);

    }


}
