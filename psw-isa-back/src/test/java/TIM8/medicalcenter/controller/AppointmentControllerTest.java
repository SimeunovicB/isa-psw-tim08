package TIM8.medicalcenter.controller;

import TIM8.medicalcenter.MedicalcenterApplication;
import TIM8.medicalcenter.Security.TokenUtils;
import TIM8.medicalcenter.dto.*;
import TIM8.medicalcenter.dto.Request.PredefAppointmentDTORequest;
import TIM8.medicalcenter.model.users.Person;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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

import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = MedicalcenterApplication.class
)

@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties",properties = {"security.basic.enabled=false", "management.security.enabled=false"})

class AppointmentControllerTest {

    private static final String URL_PREFIX = "/api/appointment/";
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    int randomServerPort;

    private String accessToken;
    private ObjectMapper jsonMapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
    private static final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
    private static int id = 1;

    @Autowired
    MockMvc mockMvc;
    @Autowired
    TokenUtils tokenUtils;
    @Autowired
    private AuthenticationManager authenticationManager;

    String jwt;

    @Test()
    void findClinics() throws Exception {
        final Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken("pera.peric@gmail.com"
                        ,"123"));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("login request: username:" + authentication.getName());

        Person person = (Person) authentication.getPrincipal();
        System.out.println(person);
        jwt =tokenUtils.generateToken(person);

        String mvcResult1 = mockMvc.perform(get(URL_PREFIX+"findClinic?date=&type=")
                .header("Authorization", jwt)).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Collection<ClinicDTO> readValues = new ObjectMapper().readValue(
                mvcResult1, new TypeReference<Collection<ClinicDTO>>() { }
        );
        assertEquals(1,readValues.size());
        String mvcResult2 = mockMvc.perform(get(URL_PREFIX+"findClinic?date=2020-02-02&type=Pregledcina")
                .header("Authorization", jwt)).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        readValues = new ObjectMapper().readValue(
                mvcResult2, new TypeReference<Collection<ClinicDTO>>() { }
        );
        assertEquals(0,readValues.size());

        String mvcResult3 = mockMvc.perform(get(URL_PREFIX+"findClinic?date=2020-03-02&type=Pedijatar")
                .header("Authorization", jwt)).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        readValues = new ObjectMapper().readValue(
                mvcResult3, new TypeReference<Collection<ClinicDTO>>() { }
        );
        assertEquals(0,readValues.size());

        String mvcResult4 = mockMvc.perform(get(URL_PREFIX+"findClinic?date=2020-02-02&type=Rutinski pregled")
                .header("Authorization", jwt)).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        readValues = new ObjectMapper().readValue(
                mvcResult4, new TypeReference<Collection<ClinicDTO>>() { }
        );

        assertEquals(1,readValues.size());
        for (ClinicDTO c:readValues) {
            assertEquals(1,c.getId());
        }
    }

    @Test
    void findClinicClient() throws URISyntaxException, JsonProcessingException {

        final Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken("pera.peric@gmail.com"
                        ,"123"));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("login request: username:" + authentication.getName());
        Person person = (Person) authentication.getPrincipal();
        System.out.println(person);
        jwt =tokenUtils.generateToken(person);
        System.out.println(jwt);

        /////
        final String baseUrl = "http://localhost:"+randomServerPort+URL_PREFIX+"findClinic?date=&type=";
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("AUTH_HEADER","Bearer " + jwt);
        HttpEntity<Object> httpEntity = new HttpEntity<>(null,headers);
        ResponseEntity<String> responseEntity =
                restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class);
        System.out.println(restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class).getHeaders());
        Assert.assertEquals(200, responseEntity.getStatusCodeValue());

        final String baseUrl1 = "http://localhost:"+randomServerPort+URL_PREFIX+"findClinic?date=2020-02-02&type=Pregledcina";
        uri = new URI(baseUrl1);
        responseEntity =
                restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class);
        System.out.println(restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class).getHeaders());
        List<ObjectReader> a = new ArrayList<>();
        Collection<ClinicDTO> readValues1 = new ObjectMapper().readValue(
                responseEntity.getBody(), new TypeReference<Collection<ClinicDTO>>() { }
        );
        assertEquals(0,readValues1.size());

        final String baseUrl2 = "http://localhost:"+randomServerPort+URL_PREFIX+"findClinic?date=2020-03-02&type=Pedijatar";
        uri = new URI(baseUrl2);
        headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("AUTH_HEADER","Bearer " + jwt);
        httpEntity = new HttpEntity<>(null,headers);
        responseEntity =
                restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class);
        Collection<ClinicDTO> readValues = new ObjectMapper().readValue(
                responseEntity.getBody(), new TypeReference<Collection<ClinicDTO>>() { }
        );
        assertEquals(0,readValues.size());

        final String baseUr2 = "http://localhost:"+randomServerPort+URL_PREFIX+"findClinic?date=2020-02-08&type=Operacija";
        uri = new URI(baseUr2);
        headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("AUTH_HEADER","Bearer " + jwt);
        httpEntity = new HttpEntity<>(null,headers);
        responseEntity =
                restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class);
        readValues = new ObjectMapper().readValue(
                responseEntity.getBody(), new TypeReference<Collection<ClinicDTO>>() { }
        );
        assertEquals(1,readValues.size());


    }

    @Test
    void findDoctorsClient() throws URISyntaxException, JsonProcessingException {
        final Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken("pera.peric@gmail.com"
                        ,"123"));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("login request: username:" + authentication.getName());
        Person person = (Person) authentication.getPrincipal();
        System.out.println(person);
        jwt =tokenUtils.generateToken(person);

        final String baseUrl = "http://localhost:"+randomServerPort+URL_PREFIX+"findClinic/doctors?clinicName=&date=&type=";
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("AUTH_HEADER","Bearer " + jwt);
        HttpEntity<Object> httpEntity = new HttpEntity<>(null,headers);
        ResponseEntity<String> responseEntity =
                restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class);
        Assert.assertEquals(200, responseEntity.getStatusCodeValue());

        final String baseUrl2 = "http://localhost:"+randomServerPort+URL_PREFIX+"findClinic/doctors?clinicName=clinic1&date=2020-02-02&type=Operacija";
        uri = new URI(baseUrl2);
        headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("AUTH_HEADER","Bearer " + jwt);
        responseEntity =
                restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class);
        Assert.assertEquals(200, responseEntity.getStatusCodeValue());


    }
    @Test
    void findDoctors() throws Exception {
        final Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken("pera.peric@gmail.com"
                        ,"123"));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("login request: username:" + authentication.getName());

        Person person = (Person) authentication.getPrincipal();
        System.out.println(person);
        jwt =tokenUtils.generateToken(person);
        String mvcResult1 = mockMvc.perform(get(URL_PREFIX+"findClinic/doctors?clinicName=clinic1&date=2020-03-02&type=Pedijatar")
                .header("Authorization", jwt))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Collection<DoctorDTO> readValues = new ObjectMapper().readValue(
                mvcResult1, new TypeReference<Collection<DoctorDTO>>() { }
        );
        assertEquals(0,readValues.size());

        String mvcResult2 = mockMvc.perform(get(URL_PREFIX+"findClinic/doctors?clinicName=&date=&type=Hirurg")
                .header("Authorization", jwt))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        readValues = new ObjectMapper().readValue(
                mvcResult2, new TypeReference<Collection<DoctorDTO>>() { }
        );
        assertEquals(0,readValues.size());

        String mvcResult3 = mockMvc.perform(get(URL_PREFIX+"findClinic/doctors?clinicName=&date=&type=").header("Authorization", jwt))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        readValues = new ObjectMapper().readValue(
                mvcResult3, new TypeReference<Collection<DoctorDTO>>() { }
        );
        assertEquals(0,readValues.size());

    }

    @Test
    void reservePedefAppClient() throws URISyntaxException {
        final Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken("pera.peric@gmail.com"
                        ,"123"));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("login request: username:" + authentication.getName());

        Person person = (Person) authentication.getPrincipal();
        System.out.println(person);
        jwt =tokenUtils.generateToken(person);
        final String baseUrl = "http://localhost:"+randomServerPort+URL_PREFIX+"reservePredef";
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        System.out.println("AAA: "+jwt);
        headers.add("AUTH_HEADER","Bearer " + jwt);
        PredefAppointmentDTORequest req = new PredefAppointmentDTORequest(1L,4L);
        HttpEntity<Object> httpEntity = new HttpEntity<>(req,headers);
        ResponseEntity<String> result =
                restTemplate.exchange(uri, HttpMethod.POST, httpEntity, String.class);
        Assert.assertEquals(202, result.getStatusCodeValue());
    }
    @Test
    void reservePredefApp() throws Exception {
        final Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken("pera.peric@gmail.com"
                        ,"123"));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("login request: username:" + authentication.getName());

        Person person = (Person) authentication.getPrincipal();
        jwt =tokenUtils.generateToken(person);
        PredefAppointmentDTORequest req = new PredefAppointmentDTORequest(1L,4L);
        mockMvc.perform(post(URL_PREFIX+"reservePredef").header("Authorization", jwt)
                .contentType(contentType)
                .content(jsonMapper.writeValueAsString(req)
                )).andExpect(status().isAccepted());
    }

    @Test
    void createPredef() throws Exception {
        final Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken("dragan.draganovic@gmail.com"
                        ,"123"));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("login request: username:" + authentication.getName());

        Person person = (Person) authentication.getPrincipal();
        System.out.println(person);
        jwt =tokenUtils.generateToken(person);
        CreatePredefDTO req = new CreatePredefDTO(2L,1L,1L,100,10,"2020-02-02-18");
        mockMvc.perform(post(URL_PREFIX+"createPredef")
                .contentType(contentType).header("Authorization", jwt)
                .content(jsonMapper.writeValueAsString(req)
                )).andExpect(status().isOk());
    }
    @Test
    void createPredefApp() throws Exception {
        final Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken("pera.peric@gmail.com"
                        ,"123"));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("login request: username:" + authentication.getName());

        Person person = (Person) authentication.getPrincipal();
        System.out.println(person);
        jwt =tokenUtils.generateToken(person);
        AppointmentController.Req req = new AppointmentController.Req();
        req.patientId=5L;
        req.id=5;
        req.date="2020-02-02";
        req.type="Pedijatar";
        mockMvc.perform(post(URL_PREFIX+"makeApp")
                .contentType(contentType).header("AUTH_HEADER", jwt)
                .content(jsonMapper.writeValueAsString(req)
                )).andExpect(status().isOk());

    }
    @Test
    void makeApp() throws Exception {
        final Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken("pera.peric@gmail.com"
                        ,"123"));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("login request: username:" + authentication.getName());

        Person person = (Person) authentication.getPrincipal();
        jwt =tokenUtils.generateToken(person);
        System.out.println(jwt);
        AppointmentController.Req req = new AppointmentController.Req();
        req.patientId=5L;
        req.id=5;
        req.date="2020-02-02";
        req.type="Pedijatar";
        mockMvc.perform(post(URL_PREFIX+"makeApp").header("AUTH_HEADER", jwt)
                .contentType(contentType)
                .content(jsonMapper.writeValueAsString(req)
                )).andExpect(status().isOk());
    }
}