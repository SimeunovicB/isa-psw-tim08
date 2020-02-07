package TIM8.medicalcenter.controller;

import TIM8.medicalcenter.Security.Auth.JwtAuthenticationRequest;
import TIM8.medicalcenter.Security.TokenUtils;
import TIM8.medicalcenter.dto.PersonDTO;
import TIM8.medicalcenter.dto.RegistrationDTO;
import TIM8.medicalcenter.exception.ResourceConflictException;
import TIM8.medicalcenter.model.users.Patient;
import TIM8.medicalcenter.model.users.Person;
import TIM8.medicalcenter.model.security.UserTokenState;
import TIM8.medicalcenter.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.HTML;
import java.io.IOException;


@RestController
@RequestMapping(value = "/auth",produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {
    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest)
        throws AuthenticationException, IOException {
        final Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername()
                        ,authenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("login request: username:" + authentication.getName());

        Person person = (Person) authentication.getPrincipal();
        System.out.println(person);
        String jwt;
        jwt =tokenUtils.generateToken(person);
        int expiresIn = tokenUtils.getExpiredIn();
        return ResponseEntity.ok(new UserTokenState(jwt,expiresIn));
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ResponseEntity<?> addUser(@RequestBody RegistrationDTO reguest, UriComponentsBuilder ucBuilder){
        Person person = personService.findOneByUsername(reguest.getUsername());
        if(person != null){
            throw new ResourceConflictException(person.getId(), "Username already exists");
        }
        Person person1 = personService.save(reguest,"PENDING","ROLE_USER");
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/user/{userId}").buildAndExpand(person1.getId()).toUri());
        return new ResponseEntity<>(new PersonDTO(person1), HttpStatus.CREATED);
    }



    @RequestMapping(value = "/refresh", method = RequestMethod.POST)
    public ResponseEntity<?> refreshAuthenticationToken(HttpServletRequest request) {

        String token = tokenUtils.getToken(request);
        String username = this.tokenUtils.getUsernameFromToken(token);
        Person user = (Person) this.personService.loadUserByUsername(username);

        if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshedToken = tokenUtils.refreshToken(token);
            int expiresIn = tokenUtils.getExpiredIn();

            return ResponseEntity.ok(new UserTokenState(refreshedToken, expiresIn));
        } else {
            UserTokenState userTokenState = new UserTokenState();
            return ResponseEntity.badRequest().body(userTokenState);
        }
    }
    @RequestMapping(value = "/activateAccount/{id}",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    public String activateAccount(@PathVariable Long id){
        Person p = personService.findOneById(id);
        if(p.getStatus().equalsIgnoreCase("ACCEPTED")){
            personService.updatePersonStatus("ACTIVE",id);
            return ("<html>\n" +
                    "    <head></head>\n" +
                    "    <body>\n" +
                    "            <h4>Uspesno ste aktivirali vas nalog, mozete se ulogovati na liknu: <a href=\"http://localhost:4200/login\">LOGIN</a></h4>\n" +
                    "    </body>\n" +
                    "</html>");

        }else{
            return ("<html>\n" +
                    "    <head></head>\n" +
                    "    <body>\n" +
                    "            <h4>Nije moguce aktivirati vas nalog ili je nalog vec aktivan</h4>\n" +
                    "    </body>\n" +
                    "</html>");

        }


    }

}
