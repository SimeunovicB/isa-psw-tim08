package TIM8.medicalcenter.controller;

import TIM8.medicalcenter.MedicalcenterApplication;
import TIM8.medicalcenter.Security.TokenUtils;
import TIM8.medicalcenter.dto.ClinicDTO;
import TIM8.medicalcenter.dto.MakeAppointmentDTORequest;
import TIM8.medicalcenter.model.users.Person;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = MedicalcenterApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")

class AdministratorControllerTest {

    private static final String URL_PREFIX = "/api/administrator/";
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    TokenUtils tokenUtils;

    @LocalServerPort
    int randomServerPort;

    private String accessToken;
    private ObjectMapper jsonMapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
    private static final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
    private static int id = 1;

    @Autowired
    private AuthenticationManager authenticationManager;

    String jwt;
    @Autowired
    MockMvc mockMvc;
    @Test
    public void makeApp() throws URISyntaxException
    {
        final Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken("dragan.draganovic@gmail.com"
                        ,"123"));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("login request: username:" + authentication.getName());

        Person person = (Person) authentication.getPrincipal();
        System.out.println(person);
        jwt =tokenUtils.generateToken(person);
        final String baseUrl = "http://localhost:"+randomServerPort+URL_PREFIX+"makeAppointment";
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        System.out.println("AAA: "+jwt);
        headers.add("AUTH_HEADER","Bearer " + jwt);
        SimpleDateFormat formatter6=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date1=new Date();
        try {
            date1 = formatter6.parse("2020-02-02 18:02:45");
        }
        catch(Exception e) {}
        MakeAppointmentDTORequest req = new MakeAppointmentDTORequest("Hirurg",date1,
                100,10,1L,2L,3L,1L);
        HttpEntity<Object> httpEntity = new HttpEntity<>(req,headers);
        ResponseEntity<String> result =
            restTemplate.exchange(uri, HttpMethod.POST, httpEntity, String.class);
        Assert.assertEquals(200, result.getStatusCodeValue());
    }
    @Test
    void makeAppoitment() throws Exception {
        final Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken("dragan.draganovic@gmail.com"
                        ,"123"));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("login request: username:" + authentication.getName());

        Person person = (Person) authentication.getPrincipal();
        System.out.println(person);
        jwt =tokenUtils.generateToken(person);
        SimpleDateFormat formatter6=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date1=new Date();
        try {
            date1 = formatter6.parse("2020-02-02 18:02:45");
        }
        catch(Exception e) {
        }
        MakeAppointmentDTORequest req = new MakeAppointmentDTORequest("Hirurg",date1,
                100,10,1L,2L,3L,4L);
        String mvcResult1=mockMvc.perform(post(URL_PREFIX+"makeAppointment")
                .contentType(contentType).header("Authorization", jwt)
                .content(jsonMapper.writeValueAsString(req)
                )).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertEquals("",mvcResult1);

    }
}