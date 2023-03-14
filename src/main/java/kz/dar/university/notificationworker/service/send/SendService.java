package kz.dar.university.notificationworker.service.send;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SendService implements ISendService {

    @Autowired
    private JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String addressFrom;

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {

        try {

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(addressFrom);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);

            log.info("Message before sending: {}", message);

            emailSender.send(message);

        } catch (MailException exception) {

            exception.printStackTrace();

        }

    }

}
