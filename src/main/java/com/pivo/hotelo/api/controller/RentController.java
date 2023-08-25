package com.pivo.hotelo.api.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pivo.hotelo.api.DTO.input.RentInput;
import com.pivo.hotelo.api.DTO.output.RentOutput;
import com.pivo.hotelo.api.assembler.RentMapper;
import com.pivo.hotelo.api.controller.openapimodel.RentControllerOpenApi;
import com.pivo.hotelo.domain.model.Rent;
import com.pivo.hotelo.domain.service.CloudStorageService;
import com.pivo.hotelo.domain.service.RentReportService;
import com.pivo.hotelo.domain.service.RentService;

import jakarta.validation.Valid;

@CrossOrigin(methods = RequestMethod.GET)
@RestController
@RequestMapping(value = "/reservas")
public class RentController implements RentControllerOpenApi {

	@Autowired
	private RentReportService rentReportService;
	
	@Autowired
	private RentService rentRoomService;
	
	@Autowired
	private RentMapper rentMapper;
	
	@Autowired
	private CloudStorageService cloudStorageService;
		
	@GetMapping()
	public ResponseEntity<CollectionModel<RentOutput>> findAll() {
		
		CollectionModel<RentOutput> list = rentMapper.toCollectionModel(rentRoomService.findAll()); 
		CollectionModel<RentOutput> rentOut = CollectionModel.of(list);
		
		
		return ResponseEntity.ok()
				.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
				.body(rentOut);
	}
	
	@GetMapping(value = "/{rentId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RentOutput> findRent(@PathVariable Long rentId ) {
		
		RentOutput rent = rentMapper.toModel(rentRoomService.findOne(rentId)); 	
		
		return ResponseEntity.ok()
				.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
				.body(rent);
	}
	
	@GetMapping(value = "/{rentId}", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> findRentPdf(@PathVariable Long rentId) {
		
		byte[] bytesPdf = rentReportService.rentReport(rentId);
		
		var headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reserva.pdf");
		
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).
				headers(headers)
				.body(bytesPdf); 	
	}
	
	@GetMapping(value = "/cliente/{clientId}")
	public ResponseEntity<CollectionModel<RentOutput>> findRentsByClient(@PathVariable Long clientId) {
		
		CollectionModel<RentOutput> list = rentMapper.toCollectionModel(rentRoomService.findRentByClient(clientId));
		
		CollectionModel<RentOutput> rentOut = CollectionModel.of(list);
		return ResponseEntity.ok()
				.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
				.body(rentOut);
	}
	
	@PostMapping()
	public RentOutput rentThis(@Valid @RequestBody RentInput rentInput,@RequestParam(required = false) boolean s3) {
		Rent rent =  rentRoomService.add(rentMapper.toDomainModel(rentInput));
		
		if(s3) {
			//-----------------S3------------------
			cloudStorageService.storeInCloud(rent);
			//-----------------S3------------------
			}
		return rentMapper.toModel(rent);
	}
	
	//-----------------S3----------------------------------------
	
	@GetMapping(value = "/{rentId}/s3")
	public RentOutput getFromS3(@PathVariable Long rentId ) {
	 	
		Rent rent = rentRoomService.findOne(rentId);
	 	
		return rentMapper.toModel(cloudStorageService.getObject(rent.getFileName()));
	}
}
