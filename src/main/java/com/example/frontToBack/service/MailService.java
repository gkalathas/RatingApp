package com.example.frontToBack.service;

import com.example.frontToBack.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
@Slf4j
public class MailService {

    private final JavaMailSender mailSender;

    private final Environment environment;

    private final TemplateEngine templateEngine;

    private final FeedbackService feedbackService;



    @Autowired
    public MailService(JavaMailSender mailSender, Environment environment,
                        TemplateEngine templateEngine, FeedbackService feedbackService) {
        this.mailSender = mailSender;
        this.environment = environment;
        this.templateEngine = templateEngine;
        this.feedbackService = feedbackService;
    }

    //need to check RabbitMQ and ActiveMQ message Queues for async requests
    
    public void sendEmail( User user) throws MessagingException, UnsupportedEncodingException {

        String mailFrom = environment.getProperty("spring.mail.properties.mail.smtp.from");
        String mailFromName = environment.getProperty("mail.from.name", "Identity");



            final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            final MimeMessageHelper mimeMessageHelper;

            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setTo(user.getEmail());
            mimeMessageHelper.setSubject("Welcome " + user.getFirstName() + " " + user.getLastName());
            mimeMessageHelper.setFrom(new InternetAddress(mailFrom, mailFromName));
            mimeMessageHelper.setText(mailContentBuilder(user), true);

            ClassPathResource path = new ClassPathResource("images/logo.png");
            mimeMessageHelper.addInline("logo",path, "image/png");


            mailSender.send(mimeMessage);
            log.info("Campaign email sent");
    }


    private String mailContentBuilder(User user) {
        final Context context = new Context(LocaleContextHolder.getLocale());
        context.setVariable("email", user.getEmail());
        context.setVariable("firstname", user.getFirstName());
        context.setVariable("lastname", user.getLastName());
        context.setVariable("name", user.getUsername());
        context.setVariable("logo", "images/logo.png");
        context.setVariable("url", "http://localhost:8080/"+ user.hashCode());

        final String htmlContent = this.templateEngine.process("mailTemplate", context);

        return htmlContent;
    }

}
