package br.com.pvprojects.email.service.impl;

import br.com.pvprojects.email.config.SendEmail;
import br.com.pvprojects.email.service.EmailService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
@Log4j2
public class EmailServiceImpl implements EmailService {

    private final SendEmail sendEmail;

    @Value("${spring.mail.username}")
    private String sender;

    @Value("${spring.mail.recipient}")
    private String recipient;

    @Value("${spring.mail.title}")
    private String title;

    public EmailServiceImpl(SendEmail sendEmail) {
        this.sendEmail = sendEmail;
    }

    @Override
    public void sendEmail() {
        log.info("Preparando para disparar email");

        Map<String, Object> map = new HashMap<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime data = LocalDateTime.now();
        map.put("data", dtf.format(data));
        map.put("nome","Paulo");

        String template= "email/sucesso";

        sendEmail.enviarEmail(sender, Arrays.asList(recipient), title, template, map);
    }
}