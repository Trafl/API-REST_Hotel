package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.model.Rent;

public interface CloudStorageService {

	void storeInCloud(Rent rent);
	
	Rent getObject(String fileName);
	
	void deleteFromCloud(String fileName);
	
}
