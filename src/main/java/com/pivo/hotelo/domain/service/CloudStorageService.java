package com.pivo.hotelo.domain.service;

import com.pivo.hotelo.domain.model.Rent;

public interface CloudStorageService {

	void storeInCloud(Rent rent);
	
	Rent getObject(String fileName);
	
}
