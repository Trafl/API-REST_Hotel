package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.DTO.input.RentInput;
import com.algaworks.algafood.api.DTO.output.RentOutput;
import com.algaworks.algafood.api.assembler.RentMapper;
import com.algaworks.algafood.domain.model.Rent;
import com.algaworks.algafood.domain.service.CloudStorageService;
import com.algaworks.algafood.domain.service.RentReportService;
import com.algaworks.algafood.domain.service.RentService;

import jakarta.validation.Valid;

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
	public List<RentOutput> findAll() {
		return rentMapper.toCollectionOuputModel(rentRoomService.findAll()); 	
	}
	
	@GetMapping(value = "/{rentId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public RentOutput findRent(@PathVariable Long rentId ) {
		return rentMapper.toOutputModel(rentRoomService.findOne(rentId)); 	
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
	public List<RentOutput> findRentsByClient(@PathVariable Long clientId) {
		return rentMapper.toCollectionOuputModel(rentRoomService.findRentByClient(clientId)); 	
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
