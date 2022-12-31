package com.example.frontToBack.service;


import com.example.frontToBack.model.User;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailContentBuilder {

    private final TemplateEngine templateEngine;

    public MailContentBuilder(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    String build(User user) {
        Context context = new Context(LocaleContextHolder.getLocale());
        context.setVariable("email", user.getEmail());
        context.setVariable("name", user.getUsername());
        context.setVariable("logo", "images/OTS-logo-moto.png");
        context.setVariable("url", "http://localhost:8080"+ user.hashCode());

        final String htmlContent = this.templateEngine.process("templates/mailTemplate.html", context);

        return htmlContent;
    }
}