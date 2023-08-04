package com.pivo.hotelo.api.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pivo.hotelo.api.DTO.input.RentInput;
import com.pivo.hotelo.api.DTO.output.RentOutput;
import com.pivo.hotelo.api.assembler.RentMapper;
import com.pivo.hotelo.domain.model.Rent;
import com.pivo.hotelo.domain.service.CloudStorageService;
import com.pivo.hotelo.domain.service.RentReportService;
import com.pivo.hotelo.domain.service.RentService;

import jakarta.validation.Valid;

@CrossOrigin(methods = RequestMethod.GET)
@RestController
@RequestMapping(value = "/reservas")
public class RentController {

	@Autowired
	private RentReportService rentReportService;
	
	@Autowired
	private RentService rentRoomService;
	
	@Autowired
	private RentMapper rentMapper;
	
	@Autowired
	private CloudStorageService cloudStorageService;
		
	@GetMapping()
	public ResponseEntity<List<RentOutput>> findAll() {
		List<RentOutput> list = rentMapper.toCollectionOuputModel(rentRoomService.findAll()); 
		
		return ResponseEntity.ok()
				.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
				.body(list);
	}
	
	@GetMapping(value = "/{rentId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RentOutput> findRent(@PathVariable Long rentId ) {
		
		RentOutput rent = rentMapper.toOutputModel(rentRoomService.findOne(rentId)); 	
		
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
	public ResponseEntity<List<RentOutput>> findRentsByClient(@PathVariable Long clientId) {
		
		List<RentOutput> list = rentMapper.toCollectionOuputModel(rentRoomService.findRentByClient(clientId));
		
		return ResponseEntity.ok()
				.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
				.body(list);
	}
	
	@PostMapping()
	public RentOutput rentThis(@Valid @RequestBody RentInput rentInput,@RequestParam(required = false) boolean s3) {
		Rent rent =  rentRoomService.add(rentMapper.toDomainModel(rentInput));
		
		if(s3) {
			//-----------------S3------------------
			cloudStorageService.storeInCloud(rent);
			//-----------------S3------------------
			}
		return rentMapper.toOutputModel(rent);
	}
	
	//-----------------S3----------------------------------------
	
	@GetMapping(value = "/{rentId}/s3")
	public RentOutput getFromS3(@PathVariable Long rentId ) {
	 	
		Rent rent = rentRoomService.findOne(rentId);
	 	
		return rentMapper.toOutputModel(cloudStorageService.getObject(rent.getFileName()));
	}
	
	@DeleteMapping(value = "/{rentId}/s3")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteFromS3(@PathVariable Long rentId ) {
		
		Rent rent = rentRoomService.findOne(rentId);
	 	
		cloudStorageService.deleteFromCloud(rent.getFileName());
	}
	
}
