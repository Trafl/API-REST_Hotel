package com.pivo.hotelo.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.pivo.hotelo.domain.event.EmailEvent;
import com.pivo.hotelo.domain.model.Rent;
import com.pivo.hotelo.domain.service.EmailService;
import com.pivo.hotelo.domain.service.EmailService.Message;

@Component
public class SendEmailListener {

	@Autowired
	private EmailService emailService;
	
	@TransactionalEventListener
	public void confirmRent(EmailEvent event) {
		Rent rent = event.getRent();
		
		var cliente = event.getRent().getCliente();
		
		String subText = "Senhor(a) " + cliente.getNome() +
				" sua reserva foi confirmada.";
		
		var message = Message.builder()
				.subText(subText)
				.addressee(event.getRent().getCliente().getEmail())
				.body("rent_notification.html")
				.variable("rent", rent)
				.build();
		
		emailService.enviar(message);		
	}
}
