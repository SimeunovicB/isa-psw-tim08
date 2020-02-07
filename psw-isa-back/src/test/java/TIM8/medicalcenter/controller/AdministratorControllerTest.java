package TIM8.medicalcenter.controller;

import TIM8.medicalcenter.MedicalcenterApplication;
import TIM8.medicalcenter.dto.ClinicDTO;
import TIM8.medicalcenter.dto.MakeAppointmentDTORequest;
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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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
    public void makeApp() throws URISyntaxException
    {
        final String baseUrl = "http://localhost:"+randomServerPort+URL_PREFIX+"makeAppointment";
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        SimpleDateFormat formatter6=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date1=new Date();
        try {
            date1 = formatter6.parse("2020-02-02 18:02:45");
        }
        catch(Exception e) {}
        MakeAppointmentDTORequest req = new MakeAppointmentDTORequest("Hirurg",date1,
                100,10,1L,2L,3L,4L);
        ResponseEntity<String> result = this.restTemplate.postForEntity(uri,req,String.class);
        Assert.assertEquals(200, result.getStatusCodeValue());
    }
    @Test
    void makeAppoitment() throws Exception {
        SimpleDateFormat formatter6=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date1=new Date();
        try {
            date1 = formatter6.parse("2020-02-02 18:02:45");
        }
        catch(Exception e) {
        }
        MakeAppointmentDTORequest req = new MakeAppointmentDTORequest("Hirurg",date1,
                100,10,1L,2L,3L,4L);
        mockMvc.perform(post(URL_PREFIX+"makeAppointment")
                .contentType(contentType)
                .content(jsonMapper.writeValueAsString(req)
                )).andExpect(status().isOk());

    }
}