package TIM8.medicalcenter;


import static org.junit.Assert.assertEquals;


import static org.junit.Assert.assertEquals;

import TIM8.medicalcenter.model.users.Patient;
import TIM8.medicalcenter.model.users.Person;
import TIM8.medicalcenter.service.PersonService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@SpringBootTest public class MedicalcenterApplicationTests {

	//private MockMvc mockMvc;

	//@Autowired
	//private WebApplicationContext webApplicationContext;

	//@Autowired
	private PersonService personService;

	@Before
	public void setup() {

		//this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		Patient  p = new Patient();
		p.setVersion(0);
		personService.saveForTest(p);
		personService.saveForTest(p);

	}
	/*@Test
	public void contextLoads() throws Exception {
		mockMvc.perform(get("/api/patient/findPatients?name=Pera&lastname=Peric&jmbg=0203998800062")).andExpect(status().isOk());
	}*/
	@Test(expected = ObjectOptimisticLockingFailureException.class)
	public void testOptimisticLockingScenario() {

		Person productForUserOne = personService.findOneById(1L);
		Person productForUserTwo = personService.findOneById(1L);

		productForUserOne.setFirstName("qweqweqweqw");
		productForUserTwo.setFirstName("asdasdasdasdasd");

		//verzija oba objekta je 0
		assertEquals(0, productForUserOne.getVersion());
		assertEquals(0, productForUserTwo.getVersion());

		//pokusaj cuvanja prvog objekta
		personService.saveForTest(productForUserOne);

		//pokusaj cuvanja drugog objekta - Exception!
		personService.saveForTest(productForUserTwo);
	}


}
