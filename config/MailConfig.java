package com.algaworks.brewer.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
//${USERPROFILE}/.brewer-mail.properties => arquivo ".brewer-mail.properties" criado na home do usuário
//contendo as propriedades e respectivos valores
@PropertySources({@PropertySource(value = {"file:${USERPROFILE}/.brewer-mail.properties" }
					  			, ignoreResourceNotFound = true)
})	
public class MailConfig {
	
	@Autowired
	private Environment env;
	
	@Bean
	public JavaMailSender mailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.sendgrid.net");
		mailSender.setPort(587); //587
		mailSender.setUsername(env.getProperty("brewer.mail.username"));
		mailSender.setPassword(env.getProperty("brewer.mail.password"));
		
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", true);
		//para encapsular
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.debug", false);
		//Tempo de tentativa de conexão com o servidor
		props.put("mail.smtp.connectiontimeout", 60000); //miliseconds
		
		mailSender.setJavaMailProperties(props);
		
		return mailSender;
	}

}

