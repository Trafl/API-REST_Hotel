package com.pivo.hotelo.api.controller.openapimodel;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.pivo.hotelo.api.DTO.DataAutentication;
import com.pivo.hotelo.api.DTO.input.UserInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Autenticação", description = "Gerenciador de Login")
public interface AutenticationControllerOpenApi {

	@Operation(summary = "Logar com usuarios", description = "Loga com um usuario registrado no bando de dados",
	 responses = {@ApiResponse(responseCode = "200"),
			 	  @ApiResponse(responseCode = "400", description = "Usuário inexistente ou senha inválida",
			 	  content = @Content(schema = @Schema(ref = "Problema")))
	})
	public ResponseEntity<?> login(@RequestBody @Valid DataAutentication dados);
	
	@Operation(summary = "Registrar um usuarios", description = "Cria um usuario no banco de dados",
			 responses = {@ApiResponse(responseCode = "200"),
				 	  @ApiResponse(responseCode = "409", description = "Email de usuario já esta em uso",
				 	  content = @Content(schema = @Schema(ref = "Problema")))
		})
	public  ResponseEntity<?> registrar(@RequestBody @Valid UserInput userInput);
	
}
