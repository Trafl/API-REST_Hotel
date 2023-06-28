package com.algaworks.algafood.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.algaworks.algafood.domain.exception.BusinessException;
import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.HotelNotFoundException;
import com.algaworks.algafood.domain.exception.RoomFromHotelNotFoundException;
import com.algaworks.algafood.domain.exception.RoomNotFoundException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
 
////////////////////////////////////////////////////////////////////////////	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		Throwable rootCause = ex.getRootCause();
		
		if(rootCause instanceof BindingResult) {
			return handleMethodArgumentNotValid((MethodArgumentNotValidException) rootCause, headers, status, request);
		}
		
		ProblemType problemType = ProblemType.UNREADABLE_MESSAGE;
		String detail = "O corpo da requisição está invalido. Verifique erro de sintaxe";
		
		Problem body = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(ex, body, new HttpHeaders(), status, request);
		
	}
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
			
		ProblemType problemType = ProblemType.INVALID_DATA;
		String detail = "Erro ao validar os campos informados no corpo da resposta.";
		
		BindingResult bindResult = ex.getBindingResult();
			
		List<Problem.Field> problemFields = bindResult.getFieldErrors().stream()
				.map(fieldErro ->{ 
					String message = messageSource.getMessage(fieldErro, LocaleContextHolder.getLocale());
					
					return Problem.Field.builder()
						.name(fieldErro.getField())
						.userMessage(message)
						.build();
		}).toList();
		
		Problem body = createProblemBuilder(status, problemType, detail).fields(problemFields).build();
		
		return handleExceptionInternal(ex, body, new HttpHeaders(), status, request);
		}
/////////////////////////////////////////////////////////////////////////////////////////////////
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<?> BusinessExceptionHandler(BusinessException ex, WebRequest request){
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ProblemType problemType = ProblemType.BUSINESS;
		String detail = ex.getMessage();
		
		Problem body = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(ex, body, new HttpHeaders(), status, request);
	}
	
	
	@ExceptionHandler(EntityInUseException.class)
	public ResponseEntity<?> EntityInUseExceptionHandler(EntityInUseException ex, WebRequest request){
		
		HttpStatus status = HttpStatus.CONFLICT;
		ProblemType problemType = ProblemType.ENTITY_IN_USE;
		String detail = ex.getMessage();
		
		Problem body = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(ex, body, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(HotelNotFoundException.class)
	public ResponseEntity<?> HotelNotFoundExceptionHandler(HotelNotFoundException ex, WebRequest request){
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.HOTEL_NOT_FOUND;
		String detail = ex.getMessage();
		
		Problem body = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(ex, body, new HttpHeaders(), status, request);
	}
	
	
	@ExceptionHandler(RoomNotFoundException.class)
	public ResponseEntity<?> RoomNotFoundExceptionHandler(RoomNotFoundException ex, WebRequest request){
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.ROOM_NOT_FOUND;
		String detail = ex.getMessage();
		
		Problem body = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(ex, body, new HttpHeaders(), status, request);
	} 
	
	@ExceptionHandler(RoomFromHotelNotFoundException.class)
	public ResponseEntity<?> RoomFromHotelNotFoundExceptionHandler(RoomFromHotelNotFoundException ex, WebRequest request){
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.ROOM_HOTEL_NOT_FOUND;
		String detail = ex.getMessage();
		
		Problem body = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(ex, body, new HttpHeaders(), status, request);
	} 
////////////////////////////////////////////////////////////////////////////////////////////	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatusCode statusCode, WebRequest request) {
		
		if(body == null) {
			body = Problem.builder()
					.status(statusCode.value())
					.timestamp(OffsetDateTime.now())
					.title(statusCode.toString())
					.detail(ex.getMessage())
					.build();
		}
		
		else if(body instanceof String) {
			body = Problem.builder()
					.status(statusCode.value())
					.timestamp(OffsetDateTime.now())
					.title(statusCode.toString())
					.detail((String)body)
					.build();
		}
		
		return super.handleExceptionInternal(ex, body, headers, statusCode, request);
	}
	
	private Problem.ProblemBuilder createProblemBuilder(HttpStatusCode status, ProblemType problemType, String detail) {
		
		return Problem.builder()
				.status(status.value())
				.title(problemType.getTitle())
				.detail(detail)
				.timestamp(OffsetDateTime.now());
	}
}