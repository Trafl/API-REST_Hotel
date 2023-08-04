package com.pivo.hotelo.controllers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HotelControllerTest {

	@LocalServerPort
	private int port;
		
	private String hotelTest = """
				{
				    "nome": "HotelC",
				    "descricao":"Test" ,
				    "endereco":{
				        "cep": 12451235,
				        "numero": 123,
				        "complemento": "casa",
				        "bairro": "areal",
				        "cidade": "angra"
						}
				}
			""";
	
	@BeforeEach
	public void setUp() throws Exception {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.basePath ="/hotel";
		RestAssured.port = port;
	}
	
//---------------Sucesso------------------------------------	
	@Test
	public void deveRetornaStatus200_QuandoListarTodosOsHoteis() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	@Test
	public void deveRetornaStatus201_QuandoAdicionarHotel() {
		given()
			.body(hotelTest)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	@Test
	
	public void deveConter2Hoteis_QuandoConsultarHotel(){
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", hasSize(3));
	}
	
//-----------------------Falha----------------------------------------	
	@Test
	public void deveRetornaStatus415_QuandoRecebeXML(){
		given()
			.accept(ContentType.JSON)
		.when()
			.contentType(ContentType.XML)
		.then()
			.statusCode(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
	}
	
	@Test
	public void deveRetornaStatus404_QuandoConsultarUmHotelInexistente(){
		given()
			.param("{hotelId}", 99L)
			.accept(ContentType.JSON)
		.when()
			.contentType(ContentType.XML)
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
/*	public void deveRetornaHotelException_QuandoConsultarUmHotelInexistente(){
		given()
			.param("{hotelId}", 99L)
			.accept(ContentType.JSON)
		.when()
			.contentType(ContentType.XML)
		.then()
			
	} */
}
