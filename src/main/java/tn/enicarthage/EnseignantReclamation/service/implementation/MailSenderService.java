package tn.enicarthage.EnseignantReclamation.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendNewMail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mohamedrayen.ouni@etudiant-ipeiem.utm.tn");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);

        System.out.println("mail sent successfully ......");
    }
}
