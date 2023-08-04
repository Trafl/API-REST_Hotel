package com.pivo.hotelo.infrastructure.email;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FakeEmailServiceImpl extends EmailServiceImpl {

	@Override
		public void enviar(Message message) {
			
			String  body = createTemplate(message);
		
			log.info("[FAKE E-MAIL] Para: {}\n{}", message.getAddressee(), body);
		}
}
