package com.pivo.hotelo.core.email;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties("hotelo.email")
@Component
public class EmailProperties {

	private String sender;
	private ServiceType serviceType = ServiceType.FAKE;
	
	public enum ServiceType{
		SMTP, FAKE
	}
}