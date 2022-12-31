package com.example.frontToBack.service;

import com.example.frontToBack.exceptions.MyCustomException;
import com.example.frontToBack.model.Feedback;
import com.example.frontToBack.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
@Slf4j
public class MailService {

    private final JavaMailSender mailSender;

    private final Environment environment;
    private final MailContentBuilder mailContentBuilder;

    private final FeedbackService feedbackService;



    @Autowired
    public MailService(JavaMailSender mailSender, Environment environment,
                       MailContentBuilder mailContentBuilder, FeedbackService feedbackService) {
        this.mailSender = mailSender;
        this.environment = environment;
        this.mailContentBuilder = mailContentBuilder;
        this.feedbackService = feedbackService;
    }

    //need to check RabbitMQ and ActiveMQ message Queues for async requests
    @Async
    public void sendEmail( User user) {

        String mailFrom = environment.getProperty("spring.mail.properties.mail.smtp.from");
        String mailFromName = environment.getProperty("mail.from.name", "Identity");

        //saving feedback except rating during email sending
        Feedback feedback = new Feedback();
        feedback.setInterestedId(user.getId());
        feedback.setItemId(user.getItemId());
        feedback.setRating(0);
        feedbackService.saveFeedback(feedback);

        MimeMessagePreparator messagePreparator = theMimeMessage -> {

            MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper;
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setTo(user.getEmail());
            mimeMessageHelper.setSubject("Welcome " + user.getFirstName() + " " + user.getLastName());
            mimeMessageHelper.setFrom(new InternetAddress(mailFrom, mailFromName));

            ClassPathResource path = new ClassPathResource("images/OTS-logo-moto.png");
            mimeMessageHelper.addInline("logo",path, "image/png");
            mimeMessageHelper.setText(mailContentBuilder.build(user), true);
            //mimeMessageHelper.setText(mailContentBuilder.build(email, interested));

        };
        try{
            mailSender.send(messagePreparator);
            log.info("Campaign email sent");
        }catch (MailException e) {
            throw new MyCustomException("Exception occurred when sending mail to "
                    + user.getFirstName() + " " + user.getLastName());
        }
    }

}
