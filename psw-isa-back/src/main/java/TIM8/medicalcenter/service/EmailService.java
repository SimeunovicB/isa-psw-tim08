package TIM8.medicalcenter.service;

import TIM8.medicalcenter.model.AppointmentRequest;
import TIM8.medicalcenter.model.users.Administrator;
import TIM8.medicalcenter.model.users.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment env;

    @Autowired
    private PersonService personService;

    @Autowired
    private ClinicService clinicService;

    @Async
    public void userAccepted(Person p) throws MailException, InterruptedException {

        //Simulacija duze aktivnosti da bi se uocila razlika
        System.out.println("Slanje emaila...");

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(p.getUsername());
        System.out.print(env.getProperty("spring.mail.username"));
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Primer slanja emaila pomoću asinhronog Spring taska");
        mail.setText("Pozdrav " + p.getFirstName() + "," +
                "acconut mozete aktivitari na linku:\n\n" +
                "http://localhost:9090/auth/activateAccount/" + p.getId());
        javaMailSender.send(mail);

        System.out.println("Email poslat!");
    }
    @Async
    public void userDenied(Person p) throws MailException, InterruptedException {

        //Simulacija duze aktivnosti da bi se uocila razlika
        System.out.println("Slanje emaila...");

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(p.getUsername());
        System.out.print(env.getProperty("spring.mail.username"));
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Primer slanja emaila pomoću asinhronog Spring taska");
        mail.setText("Pozdrav " + p.getFirstName() + "," +
                "na zalost ne mozemo da vam odobrimoda koristite aplikaciju");
        javaMailSender.send(mail);

        System.out.println("Email poslat!");
    }

    @Async
    public void newRequest(AppointmentRequest appointmentRequest) throws MailException, InterruptedException {


        //Simulacija duze aktivnosti da bi se uocila razlika
        System.out.println("Slanje emaila...");

        List<Administrator> admini = personService.findAdmins(personService.findDoctor(appointmentRequest.getDoctor_id()).getClinic().getId());

        for(Administrator administrator : admini) {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(administrator.getUsername());
            System.out.print(env.getProperty("spring.mail.username"));
            mail.setFrom(env.getProperty("spring.mail.username"));
            mail.setSubject("Nov zahtev za pregled/operaciju");
            mail.setText("Pozdrav " + administrator.getFirstName() + "," +
                    "imate nov zahtev za pregled/operaciju.\nLekar: "+personService.findOneById(appointmentRequest.getDoctor_id()).getFirstName()+" "
                    +personService.findOneById(appointmentRequest.getDoctor_id()).getLastName()+".\nPacijent: "+personService.findOneById(appointmentRequest.getPatient_id()).getFirstName()+" "
                    +personService.findOneById(appointmentRequest.getPatient_id()).getLastName()+"\nDatum: "+appointmentRequest.getDate().toString()+
                    "\nTip: "+appointmentRequest.getAppointment_type());
            javaMailSender.send(mail);

            System.out.println("Email poslat!");
        }
    }

    @Async
    public void declineVacationRequest(Person p, String razlog) throws MailException, InterruptedException {


        //Simulacija duze aktivnosti da bi se uocila razlika
        System.out.println("Slanje emaila...");

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(p.getUsername());
        System.out.print(env.getProperty("spring.mail.username"));
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Odbijen zahtev za godisnji");
        mail.setText("Pozdrav " + p.getFirstName() + "," +
                    "vas zahtev za godisnji odmor je odbijen. Razlog: "+razlog);
        javaMailSender.send(mail);

        System.out.println("Email poslat!");
    }

}
