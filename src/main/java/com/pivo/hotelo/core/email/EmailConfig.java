package com.pivo.hotelo.core.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pivo.hotelo.core.email.EmailProperties.ServiceType;
import com.pivo.hotelo.domain.service.EmailService;
import com.pivo.hotelo.infrastructure.email.EmailServiceImpl;
import com.pivo.hotelo.infrastructure.email.FakeEmailServiceImpl;

@Configuration
public class EmailConfig {
	
	@Autowired
	private EmailProperties emailProperties;

	@Bean
	public EmailService emailService() {
		
		if(emailProperties.getServiceType().equals(ServiceType.SMTP)){
			return new EmailServiceImpl();
		}else {
			return new FakeEmailServiceImpl();
		}
			
	}
}
