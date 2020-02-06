package TIM8.medicalcenter.controller;

import TIM8.medicalcenter.MedicalcenterApplication;
import TIM8.medicalcenter.Security.Auth.JwtAuthenticationRequest;
import TIM8.medicalcenter.dto.ClinicDTO;
import TIM8.medicalcenter.dto.DoctorDTO;
import TIM8.medicalcenter.dto.PersonDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.AssertionFailedError;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.Charset;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
}