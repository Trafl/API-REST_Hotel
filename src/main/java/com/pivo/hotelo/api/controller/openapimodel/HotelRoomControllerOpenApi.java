package com.pivo.hotelo.api.controller.openapimodel;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.pivo.hotelo.api.DTO.input.RoomInput;
import com.pivo.hotelo.api.DTO.output.RoomOutput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Quartos", description = "Gerenciador dos quartos do hotel")
public interface HotelRoomControllerOpenApi {

	@Operation(summary = "Lista os quartos", description = "Lista os quartos registrados no banco de dados.")
	public ResponseEntity<CollectionModel<RoomOutput>> findRoomsOfHotel(@Parameter(description = "ID do hotel", example = "1") Long hotelId, 
															 @Parameter(description = "Parametro para retornar todo os quartos até os alugados", example = "true")  boolean alugados );

	@Operation(summary = "Busca um quarto por ID", description = "Busca um quarto registrado no banco de dados.",
			responses = {@ApiResponse(responseCode = "200"),
				 	  @ApiResponse(responseCode = "400", description = "ID de quarto invalido",
				 	  content = @Content(schema = @Schema(ref = "Problema"))),
				 	  @ApiResponse(responseCode = "404", description = "Quarto ou hotel não encontrado",
				 	  content = @Content(schema = @Schema(ref = "Problema")))
		})
	public ResponseEntity<RoomOutput> findOneRoomOfHotel(@Parameter(description = "ID do hotel", example = "1")Long hotelId, 
														 @Parameter(description = "ID do quarto", example = "1")Long roomId);
	
	@Operation(summary = "Registra um quarto.", description = "Registar um quarto no banco de dados.")
	public RoomOutput addRoom(@Parameter(description = "ID do hotel", example = "1")Long hotelId, 
							  @RequestBody(description = "Corpo com as informações do novo quarto")RoomInput roomInput);

	@Operation(summary = "Atualiza um quarto por ID", description = "Atualiza um quarto registrado no banco de dados.",
			responses = {@ApiResponse(responseCode = "200"),
				 	  @ApiResponse(responseCode = "400", description = "ID de quarto invalido",
				 	  content = @Content(schema = @Schema(ref = "Problema"))),
				 	  @ApiResponse(responseCode = "404", description = "Quarto ou hotel não encontrado",
				 	  content = @Content(schema = @Schema(ref = "Problema")))
		})
	public RoomOutput updateRoom(@Parameter(description = "ID do hotel", example = "1")Long hotelId, 
								 @Parameter(description = "ID do quarto", example = "1")Long roomId,
								 @RequestBody(description = "Corpo com as informações para atualizar um quarto") RoomInput roomInput);
	
	@Operation(summary = "Deleta um quarto por ID", description = "Delta um quartto registrado no banco de dados.",
			responses = {@ApiResponse(responseCode = "204"),
				 	  @ApiResponse(responseCode = "400", description = "ID de quarto invalido",
				 	  content = @Content(schema = @Schema(ref = "Problema"))),
				 	  @ApiResponse(responseCode = "404", description = "Quarto não encontrado",
				 	  content = @Content(schema = @Schema(ref = "Problema"))),
					  @ApiResponse(responseCode = "409", description = "Entidade em uso",
						 	  content = @Content(schema = @Schema(ref = "Problema")))})
	public void deleteRoom(@Parameter(description = "ID do hotel", example = "1")Long roomId);
}
