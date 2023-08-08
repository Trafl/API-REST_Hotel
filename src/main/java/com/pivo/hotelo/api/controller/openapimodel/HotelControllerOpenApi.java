package com.pivo.hotelo.api.controller.openapimodel;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.pivo.hotelo.api.DTO.input.HotelInput;
import com.pivo.hotelo.api.DTO.output.HotelOutput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Hotel", description = "Gerenciador de hoteis")
public interface HotelControllerOpenApi {

	@Operation(summary = "Lista os hoteis", description = "Lista os hoteis registrados no banco de dados.")
	public ResponseEntity<List<HotelOutput>> findAll();
	

	@Operation(summary = "Busca um hotel pelo ID", description = "Busca um hotel registrado no banco de dados.",
			responses = {@ApiResponse(responseCode = "200"),
				 	  @ApiResponse(responseCode = "400", description = "ID de hotel invalido",
				 	  content = @Content(schema = @Schema(ref = "Problema"))),
				 	  @ApiResponse(responseCode = "404", description = "Hotel não encontrado",
				 	  content = @Content(schema = @Schema(ref = "Problema")))
		})
	public ResponseEntity<HotelOutput> findOne(@Parameter(description = "ID do hotel", example = "1") Long hotelId);
	
	@Operation(summary = "Registra um hotel", description = "Registra um hotel no banco de dados.")
	public HotelOutput add(@RequestBody(description = "Corpo com as informações do novo hotel") HotelInput hotelInput);
	
	
	@Operation(summary = "Atualiza um hotel", description = "Atualiza um hotel registrado no banco de dados.",
			responses = {@ApiResponse(responseCode = "200"),
				 	  @ApiResponse(responseCode = "400", description = "ID de hotel invalido",
				 	  content = @Content(schema = @Schema(ref = "Problema"))),
				 	  @ApiResponse(responseCode = "404", description = "Hotel não encontrado",
				 	  content = @Content(schema = @Schema(ref = "Problema")))
		})
	public HotelOutput update(@RequestBody(description = "Corpo com as informações para atualizar um hotel") HotelInput hotelInput, 
							  @Parameter(description = "ID do hotel", example = "1") Long hotelId);
	
	
	@Operation(summary = "Deleta um hotel", description = "Deleta um hotel registrado no banco de dados.",
			 responses = {@ApiResponse(responseCode = "204"),
				 	  @ApiResponse(responseCode = "400", description = "ID de hotel invalido",
				 	  content = @Content(schema = @Schema(ref = "Problema"))),
				 	  @ApiResponse(responseCode = "404", description = "Hotel não encontrado",
				 	  content = @Content(schema = @Schema(ref = "Problema"))),
					 @ApiResponse(responseCode = "409", description = "Entidade em uso",
						 	  content = @Content(schema = @Schema(ref = "Problema")))})
	public void delete(@Parameter(description = "ID do hotel", example = "1") Long hotelId);
}
