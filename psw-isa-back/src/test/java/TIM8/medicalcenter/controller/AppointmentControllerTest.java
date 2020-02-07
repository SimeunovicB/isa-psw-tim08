package TIM8.medicalcenter.controller;

import TIM8.medicalcenter.MedicalcenterApplication;
import TIM8.medicalcenter.Security.Auth.JwtAuthenticationRequest;
import TIM8.medicalcenter.dto.*;
import TIM8.medicalcenter.dto.Request.PredefAppointmentDTORequest;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.AssertionFailedError;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = MedicalcenterApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")

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

    @Test
    void findClinicClient() throws URISyntaxException, JsonProcessingException {

            final String baseUrl = "http://localhost:"+randomServerPort+URL_PREFIX+"findClinic?date=&type=";
            URI uri = new URI(baseUrl);
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-COM-PERSIST", "true");
            ResponseEntity<String> result = this.restTemplate.getForEntity(uri,String.class);
            Assert.assertEquals(200, result.getStatusCodeValue());

            final String baseUrl1 = "http://localhost:"+randomServerPort+URL_PREFIX+"findClinic?date=bla&type=bla";
            uri = new URI(baseUrl1);
            headers = new HttpHeaders();
            headers.set("X-COM-PERSIST", "true");
            result = this.restTemplate.getForEntity(uri,String.class);
            assertEquals(null,result.getBody());

            final String baseUrl2 = "http://localhost:"+randomServerPort+URL_PREFIX+"findClinic?date=2020-03-02&type=Pedijatar";
            uri = new URI(baseUrl2);
            headers = new HttpHeaders();
            headers.set("X-COM-PERSIST", "true");
            result = this.restTemplate.getForEntity(uri,String.class);
            System.out.println(result.getBody());
            Collection<ClinicDTO> readValues = new ObjectMapper().readValue(
                    result.getBody(), new TypeReference<Collection<ClinicDTO>>() { }
            );
            assertEquals(1,readValues.size());


    }
    @Test
    void findDoctorsClient() throws URISyntaxException, JsonProcessingException {
        final String baseUrl = "http://localhost:"+randomServerPort+URL_PREFIX+"findClinic/doctors?clinicName=&date=&type=";
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        ResponseEntity<String> result = this.restTemplate.getForEntity(uri,String.class);
        Assert.assertEquals(200, result.getStatusCodeValue());

        final String baseUrl2 = "http://localhost:"+randomServerPort+URL_PREFIX+"findClinic/doctors?clinicName=clinic1&date=2020-03-02&type=Pedijatar";
        uri = new URI(baseUrl2);
        headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        result = this.restTemplate.getForEntity(uri,String.class);
        System.out.println(result.getBody());
        Collection<ClinicDTO> readValues = new ObjectMapper().readValue(
                result.getBody(), new TypeReference<Collection<ClinicDTO>>() { }
        );
        assertEquals(1,readValues.size());
    }

    @Test()
    void findClinics() throws Exception {
        String mvcResult1 = mockMvc.perform(get(URL_PREFIX+"findClinic?date=&type=")).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Collection<ClinicDTO> readValues = new ObjectMapper().readValue(
                mvcResult1, new TypeReference<Collection<ClinicDTO>>() { }
        );
        assertEquals(1,readValues.size());

        String mvcResult2 = mockMvc.perform(get(URL_PREFIX+"findClinic?date=bla&type=bla")).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertEquals("",mvcResult2);

        String mvcResult3 = mockMvc.perform(get(URL_PREFIX+"findClinic?date=2020-03-02&type=Pedijatar")).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        readValues = new ObjectMapper().readValue(
                mvcResult3, new TypeReference<Collection<ClinicDTO>>() { }
        );
        assertEquals(1,readValues.size());

        String mvcResult4 = mockMvc.perform(get(URL_PREFIX+"findClinic?date=2020-02-01&type=Hirurg")).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        readValues = new ObjectMapper().readValue(
                mvcResult4, new TypeReference<Collection<ClinicDTO>>() { }
        );
        assertEquals(0,readValues.size());


    }

    @Test
    void findDoctors() throws Exception {
        String mvcResult1 = mockMvc.perform(get(URL_PREFIX+"findClinic/doctors?clinicName=clinic1&date=2020-03-02&type=Pedijatar")).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Collection<DoctorDTO> readValues = new ObjectMapper().readValue(
                mvcResult1, new TypeReference<Collection<DoctorDTO>>() { }
        );
        assertEquals(1,readValues.size());

        String mvcResult2 = mockMvc.perform(get(URL_PREFIX+"findClinic/doctors?clinicName=&date=&type=Hirurg")).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        readValues = new ObjectMapper().readValue(
                mvcResult2, new TypeReference<Collection<DoctorDTO>>() { }
        );
        assertEquals(0,readValues.size());

        String mvcResult3 = mockMvc.perform(get(URL_PREFIX+"findClinic/doctors?clinicName=&date=&type=")).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        readValues = new ObjectMapper().readValue(
                mvcResult3, new TypeReference<Collection<DoctorDTO>>() { }
        );
        assertEquals(0,readValues.size());

    }

    @Test
    void reservePedefAppClient() throws URISyntaxException {
        final String baseUrl = "http://localhost:"+randomServerPort+URL_PREFIX+"reservePredef";
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        PredefAppointmentDTORequest req = new PredefAppointmentDTORequest(1L,4L);
        ResponseEntity<String> result = this.restTemplate.postForEntity(uri,req,String.class);
        Assert.assertEquals(202, result.getStatusCodeValue());
    }
    @Test
    void reservePredefApp() throws Exception {
        PredefAppointmentDTORequest req = new PredefAppointmentDTORequest(1L,4L);
        mockMvc.perform(post(URL_PREFIX+"reservePredef")
                .contentType(contentType)
                .content(jsonMapper.writeValueAsString(req)
                )).andExpect(status().isAccepted());
    }

    @Test
    void createPredef() throws Exception {
        CreatePredefDTO req = new CreatePredefDTO(2L,1L,1L,100,10,"2020-02-02-18");
        mockMvc.perform(post(URL_PREFIX+"createPredef")
                .contentType(contentType)
                .content(jsonMapper.writeValueAsString(req)
                )).andExpect(status().isOk());
    }
    @Test
    void createPredefApp() throws Exception {
        AppointmentController.Req req = new AppointmentController.Req();
        req.patientId=5L;
        req.id=5;
        req.date="2020-02-02";
        req.type="Pedijatar";
        mockMvc.perform(post(URL_PREFIX+"makeApp")
                .contentType(contentType)
                .content(jsonMapper.writeValueAsString(req)
                )).andExpect(status().isOk());
    }
    @Test
    void makeApp() throws Exception {
        AppointmentController.Req req = new AppointmentController.Req();
        req.patientId=5L;
        req.id=5;
        req.date="2020-02-02";
        req.type="Pedijatar";
        mockMvc.perform(post(URL_PREFIX+"makeApp")
                .contentType(contentType)
                .content(jsonMapper.writeValueAsString(req)
                )).andExpect(status().isOk());
    }
}