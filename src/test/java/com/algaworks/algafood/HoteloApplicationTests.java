package com.algaworks.algafood;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.algaworks.algafood.api.DTO.input.HotelInput;
import com.algaworks.algafood.api.assembler.HotelMapper;
import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.HotelNotFoundException;
import com.algaworks.algafood.domain.model.Hotel;
import com.algaworks.algafood.domain.service.HotelService;

import jakarta.validation.ConstraintViolationException;


@SpringBootTest
class HoteloApplicationTests {

	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private HotelMapper mapper;
	
	@Test()
	public void deveTerSucesso_AoCadastrarHotel() {
		
		Hotel hotel = new Hotel();
		hotel.setNome("HotelTest");
		
		hotel = hotelService.add(hotel);
		
		assertThat(hotel).isNotNull();
		assertThat(hotel.getId()).isNotNull();
	}
	
	@Test
	public void deveFalhar_AoCadastrarHotelSemNome() {
		HotelInput hotel = new HotelInput();
		hotel.setNome("");
		
		ConstraintViolationException  error =
				assertThrows(ConstraintViolationException.class,
						()-> hotelService.add(mapper.toDomainModel(hotel)));
		
		assertThat(error).isNotNull();
	}

	@Test
	public void deveFalhar_QuandoExcluirHotelEmUso() {

		EntityInUseException erroEsperado =
				assertThrows(EntityInUseException.class, () -> {
					hotelService.delete(1L);
				});

		assertThat(erroEsperado).isNotNull();

	}
	
	@Test
	public void deveFalhar_QuandoExcluirHotelInexistente() {

		HotelNotFoundException erroEsperado =
				assertThrows(HotelNotFoundException.class, () -> {
					hotelService.delete(100L);
				});

		assertThat(erroEsperado).isNotNull();

	}
	
}
