package com.pivo.hotelo.api.DTO.input;

import com.pivo.hotelo.domain.model.UserRoles;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInput {

	@Email
	@NotBlank
	@Schema(example = "exemplo@email.com")
	private String email;
	
	@NotBlank
	@Schema(example = "123456")
	private String senha;
	
	@NotNull
	@Schema(example = "CLIENT")
	private UserRoles role;
	
}
