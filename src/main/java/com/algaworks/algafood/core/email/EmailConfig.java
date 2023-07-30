package com.algaworks.algafood.core.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algaworks.algafood.core.email.EmailProperties.ServiceType;
import com.algaworks.algafood.domain.service.EmailService;
import com.algaworks.algafood.infrastructure.email.EmailServiceImpl;
import com.algaworks.algafood.infrastructure.email.FakeEmailServiceImpl;

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
