package TIM8.medicalcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MedicalcenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicalcenterApplication.class, args);
	}

}
