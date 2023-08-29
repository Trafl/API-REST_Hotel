package com.pivo.hotelo.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pivo.hotelo.api.DTO.DataAutentication;
import com.pivo.hotelo.api.DTO.input.UserInput;
import com.pivo.hotelo.api.controller.openapimodel.AutenticationControllerOpenApi;
import com.pivo.hotelo.core.security.TokenJwt;
import com.pivo.hotelo.core.security.TokenService;
import com.pivo.hotelo.domain.exception.EmailInUseException;
import com.pivo.hotelo.domain.exception.EntityInUseException;
import com.pivo.hotelo.domain.model.User;
import com.pivo.hotelo.domain.repository.UserRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticationController implements AutenticationControllerOpenApi {
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserRepository repository;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid DataAutentication dados) {
		
		try {
			var tokenAuth  = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
			var authentication = manager.authenticate(tokenAuth );
			
			var tokenJWT= tokenService.tokenGeneration((User) authentication.getPrincipal());
			
			return ResponseEntity.ok(new TokenJwt(tokenJWT));
			
		}catch (Exception e) {
	        throw new EmailInUseException("Usuário inexistente ou senha inválida");
	    }
		
		
	}
	
	@Transactional
	@PostMapping("/registrar")
	public  ResponseEntity<?> registrar(@RequestBody @Valid UserInput userInput){
		
		if(repository.findByEmail(userInput.getEmail()) !=null) {
			throw new EntityInUseException("Email de usuario já esta em uso");
		}
		
		var encryptedPassword = new BCryptPasswordEncoder().encode(userInput.getSenha());
		
		User user = new User(userInput.getEmail(),encryptedPassword ,userInput.getRole());
		
		repository.save(user);
		
		return ResponseEntity.ok().build();
	}

}
