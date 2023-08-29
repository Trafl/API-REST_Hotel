package com.pivo.hotelo.api.DTO;

import io.swagger.v3.oas.annotations.media.Schema;

public record DataAutentication(@Schema(example = "exemplo@email.com") String email, @Schema(example = "123456") String senha ) {

}
