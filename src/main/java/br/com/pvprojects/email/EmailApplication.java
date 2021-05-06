package br.com.pvprojects.email;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.TimeZone;

@EnableScheduling
@SpringBootApplication
@Log4j2
public class EmailApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(EmailApplication.class,
				args);

		ConfigurableEnvironment environment = context.getEnvironment();
		String port = environment.getProperty("server.port");
		String name = environment.getProperty("info.app.name");
		String version = environment.getProperty("info.app.version");
		String hostAddress = null;
		TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
		String time = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
				.format(LocalDateTime.now());

		try {
			hostAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			log.error("Erro ao buscar o host", e);
		}

		log.info("\n" +
				"\n" +
				"| \n" +
				"| -------------------------------------------------------------------\n" +
				"| Application: \'" + name + "\' with version \'" + version + "\' is running! \n" +
				"| Access URL: \n" +
				"| Host -> " + hostAddress + ":" + port + "\n" +
				"| Time: \'" + time + "\' \n" +
				"| -------------------------------------------------------------------\n" +
				"| \n");
	}
}