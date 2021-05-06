package br.com.pvprojects.email.batch;

import br.com.pvprojects.email.service.EmailService;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
@Log4j2
public class Notifications {

    private final EmailService emailService;

    public Notifications(EmailService emailService) {
        this.emailService = emailService;
    }

    @Scheduled(cron = "${batch.cron.t1}")
    void runJob(){
        log.info("Executing job to notify.");
        emailService.sendEmail();
        log.info("Finished job to notify.");
    }
}