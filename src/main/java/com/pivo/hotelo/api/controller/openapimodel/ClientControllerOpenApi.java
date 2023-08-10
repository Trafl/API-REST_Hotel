package com.pivo.hotelo.api.controller.openapimodel;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.pivo.hotelo.api.DTO.input.ClientInput;
import com.pivo.hotelo.api.DTO.output.ClientOutput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Cliente", description = "Gerenciador de clientes")
public interface ClientControllerOpenApi {

	 @Operation(summary = "Lista os clientes", description = "Lista os clientes registrados no banco de dados.")
	 ResponseEntity<CollectionModel<ClientOutput>> findAll(); 
	
	
	 @Operation(summary = "Busca um cliente por ID", description = "Busca um cliente registrado no banco de dados.",
			 responses = {@ApiResponse(responseCode = "200"),
					 	  @ApiResponse(responseCode = "400", description = "ID de cliente invalido",
					 	  content = @Content(schema = @Schema(ref = "Problema"))),
					 	  @ApiResponse(responseCode = "404", description = "Cliente não encontrado",
					 	  content = @Content(schema = @Schema(ref = "Problema")))
			})
	 ResponseEntity<ClientOutput> findOne(@Parameter(description = "ID de um cliente", example = "1") Long clientId);
	
	 
	 @Operation(summary = "Registra um cliente", description = "Registra um cliente no banco de dados.")
	 ClientOutput add(@RequestBody(description = "Corpo com as informações do novo cliente") ClientInput clientInput); 
	
	 
	 @Operation(summary = "Atualizar um cliente", description = "Atualiza um cliente no banco de dados.",
			 responses = {@ApiResponse(responseCode = "200"),
				 	  @ApiResponse(responseCode = "400", description = "ID de cliente invalido",
				 	  content = @Content(schema = @Schema(ref = "Problema"))),
				 	  @ApiResponse(responseCode = "404", description = "Cliente não encontrado",
				 	  content = @Content(schema = @Schema(ref = "Problema")))
		})
	 ClientOutput update(@RequestBody(description = "Corpo com as informações para atualizar um cliente") ClientInput clientInput,
			 			@Parameter(description = "ID de um cliente", example = "1") Long clientId);
	
	 
	 @Operation(summary = "Deleta um cliente", description = "Deleta um cliente no banco de dados.",
			 responses = {@ApiResponse(responseCode = "204"),
				 	  @ApiResponse(responseCode = "400", description = "ID de cliente invalido",
				 	  content = @Content(schema = @Schema(ref = "Problema"))),
				 	  @ApiResponse(responseCode = "404", description = "Cliente não encontrado",
				 	  content = @Content(schema = @Schema(ref = "Problema"))),
					 @ApiResponse(responseCode = "409", description = "Entidade em uso",
						 	  content = @Content(schema = @Schema(ref = "Problema")))})
	 void delete(@Parameter(description = "ID de um cliente", example = "1") Long clientId);
	
}
	

