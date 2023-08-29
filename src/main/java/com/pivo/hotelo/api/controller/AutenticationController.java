package com.pivo.hotelo.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pivo.hotelo.api.DTO.DataAutentication;
import com.pivo.hotelo.core.security.TokenJwt;
import com.pivo.hotelo.core.security.TokenService;
import com.pivo.hotelo.domain.model.User;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AutenticationController {
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<?> login(@RequestBody @Valid DataAutentication dados) {
		
		try {
			var tokenAuth  = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
			var authentication = manager.authenticate(tokenAuth );
			
			var tokenJWT= tokenService.tokenGeneration((User) authentication.getPrincipal());
			
			return ResponseEntity.ok(new TokenJwt(tokenJWT));
			
		}catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.badRequest().body(e.getMessage());
	    }
		
		
	}

}
