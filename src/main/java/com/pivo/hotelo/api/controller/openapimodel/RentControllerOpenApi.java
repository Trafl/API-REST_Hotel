package com.pivo.hotelo.api.controller.openapimodel;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.pivo.hotelo.api.DTO.input.RentInput;
import com.pivo.hotelo.api.DTO.output.RentOutput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Reservas", description = "Gerenciador de reservas")
public interface RentControllerOpenApi {

	@Operation(summary = "Lista as reservas", description = "Lista as reservas registradas no banco de dados.")
	public ResponseEntity<CollectionModel<RentOutput>> findAll();

	@Operation(summary = "Busca uma reserva por ID", description = "Busca uma reserva registrada no banco de dados e tambem pode ser gerado um PDF como comprovante.",
			responses = {@ApiResponse(responseCode = "200", content = {
					 @Content(mediaType = "application/json", schema = @Schema(implementation = RentOutput.class)),
					 @Content(mediaType = "application/pdf", schema = @Schema())
			
			}),
				 	  @ApiResponse(responseCode = "400", description = "ID de reserva invalido",
				 	  content = @Content(schema = @Schema(ref = "Problema"))),
				 	  @ApiResponse(responseCode = "404", description = "Reserva não encontrada",
				 	  content = @Content(schema = @Schema(ref = "Problema")))
		})
	public ResponseEntity<RentOutput> findRent(@Parameter(description = "ID de uma reserva", example = "1") Long rentId );
	
	@Operation(hidden = true)
	public ResponseEntity<byte[]> findRentPdf(@Parameter(description = "ID de uma reserva", example = "1") Long rentId);
	
	@Operation(summary = "Busca uma reserva pelo ID do cliente", description = "Busca uma reserva registrada no banco de dados pelo ID do cliente.")
	public ResponseEntity<CollectionModel<RentOutput>> findRentsByClient(@Parameter(description = "ID de um cliente", example = "1")Long clientId);
	
	
	@Operation(summary = "Registra uma reserva", 
			description = "Registra uma reserva no bando de dados local e/ou na AmazonS3 é um Email de confirmação e enviado ao cliente.",
			responses = {@ApiResponse(responseCode = "200"),
			  @ApiResponse(responseCode = "400", description = "Este quarto não esta disponivel.",
		 	  content = @Content(schema = @Schema(ref = "Problema")))})
				 	
	public RentOutput rentThis(@RequestBody(description = "Corpo com as informações de uma nova reserva")RentInput rentInput, 
							   @Parameter(description = "Parametro para habilitar o registro na AmazonS3", example = "true")boolean s3);
	
	@Operation(summary = "Busca uma reserva na AmazonS3", description = "Busca uma reserva diretamente da AmazonS3",
			responses = {@ApiResponse(responseCode = "200"),
				 	  @ApiResponse(responseCode = "400", description = "ID de reserva invalido",
				 	  content = @Content(schema = @Schema(ref = "Problema"))),
				 	  @ApiResponse(responseCode = "404", description = "Reserva não encontrada",
				 	  content = @Content(schema = @Schema(ref = "Problema")))
		})
	public RentOutput getFromS3(@Parameter(description = "ID de uma reserva", example = "1")Long rentId );
	

	@Operation(summary = "Deleta uma reserva na AmazonS3", description = "Deleta uma reserva diretamente da AmazonS3",
			responses = {@ApiResponse(responseCode = "204"),
				 	  @ApiResponse(responseCode = "400", description = "ID de reserva invalido",
				 	  content = @Content(schema = @Schema(ref = "Problema"))),
				 	  @ApiResponse(responseCode = "404", description = "Reserva não encontrada",
				 	  content = @Content(schema = @Schema(ref = "Problema")))
		})
	public void deleteFromS3(@Parameter(description = "ID de uma reserva", example = "1")Long rentId );
}
