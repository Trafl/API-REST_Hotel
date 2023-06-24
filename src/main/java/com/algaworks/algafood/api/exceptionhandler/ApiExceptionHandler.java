package com.algaworks.algafood.api.exceptionhandler;

import java.time.OffsetDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.algaworks.algafood.domain.exception.BusinessException;
import com.algaworks.algafood.domain.exception.HotelNotFoundException;
import com.algaworks.algafood.domain.exception.RoomNotFoundException;

@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<?> BusinessExceptionHandler(BusinessException e){
		Problem problem = Problem.builder()
				.timestamp(OffsetDateTime.now())
				.message(e.getMessage())
				.build();
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problem);
	}
	
	
	@ExceptionHandler(HotelNotFoundException.class)
	public ResponseEntity<?> HotelNotFoundExceptionHandler(HotelNotFoundException e){
		Problem problem = Problem.builder()
				.timestamp(OffsetDateTime.now())
				.message(e.getMessage())
				.build();
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problem);
	}
	
	
	@ExceptionHandler(RoomNotFoundException.class)
	public ResponseEntity<?> RoomNotFoundExceptionHandler(RoomNotFoundException e){
		Problem problem = Problem.builder()
				.timestamp(OffsetDateTime.now())
				.message(e.getMessage())
				.build();
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problem);
	}
	
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<?> HttpMediaTypeNotSupportedExceptionHandler(){
		Problem problem = Problem.builder()
				.timestamp(OffsetDateTime.now())
				.message("O tipo de mídia não é aceito")
				.build();
		
		return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(problem);
	}
}
