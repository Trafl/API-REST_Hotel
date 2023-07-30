package com.algaworks.algafood.infrastructure.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.algaworks.algafood.core.email.EmailProperties;
import com.algaworks.algafood.domain.service.EmailService;

import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

public class EmailServiceImpl implements EmailService {

	@Autowired
	private EmailProperties properties;
	
	@Autowired
	private Configuration configuration;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	public void enviar(Message message) {
		try {	
		MimeMessage mimeMessage = createMimeMessage(message);
		
		mailSender.send(mimeMessage);
		
		} catch (Exception e) {
			throw new MailException("Não foi possivel enviar o e-mail.", e);
		}
		
	}
	
	protected MimeMessage createMimeMessage(Message message) throws MessagingException {

		String body = createTemplate(message);
							
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,"UTF-8");
		
		helper.setFrom(properties.getSender());
		helper.setTo(message.getAddressee());
		helper.setSubject(message.getSubText());
		helper.setText(body, true);
		
		return mimeMessage;
	}

	protected String createTemplate(Message message)  {
		try {
			Template template = configuration.getTemplate(message.getBody());
		
			return FreeMarkerTemplateUtils.processTemplateIntoString(template, message.getVariables());
		
		} catch (Exception e) {
			throw new MailException("Não foi possivel montar a template do e-mail.", e);
		}
	}
}
