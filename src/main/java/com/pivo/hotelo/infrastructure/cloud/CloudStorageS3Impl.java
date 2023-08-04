package com.pivo.hotelo.infrastructure.cloud;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.pivo.hotelo.core.amazons3.StorageProperties;
import com.pivo.hotelo.domain.model.Rent;
import com.pivo.hotelo.domain.service.CloudStorageService;

@Service
public class CloudStorageS3Impl implements CloudStorageService {

	@Autowired
	private AmazonS3 amazonS3;
	
	@Autowired
	private StorageProperties storageProperties;
	
	public static ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());	
	
	@Override
	public void storeInCloud(Rent rent)  {
		try {
			
			String rentJson = objectMapper.writeValueAsString(rent);
			
			byte[] rentJsonBytes = rentJson.getBytes();
			
			ByteArrayInputStream inputStream = new ByteArrayInputStream(rentJsonBytes);
			
			String caminhoArquivo = getFilePath(rent.getFileName());
			
			ObjectMetadata metadata = new ObjectMetadata();
		    
		    amazonS3.putObject(storageProperties.getS3().getBucket(),
		    		caminhoArquivo,
		    		inputStream, 
		    		metadata);
			
			}
			catch(AmazonServiceException e) {
				throw new StorageCloudException("Erro ao armazenar o objeto do Amazon S3: " + e.getErrorMessage());
			} 
			catch (JsonProcessingException e) {
				e.printStackTrace();
			}
	}
		
	public Rent getObject(String fileName) {
		
		String caminho = getFilePath(fileName);
		
        try {
            S3Object s3Object = amazonS3.getObject(storageProperties.getS3().getBucket(), caminho);
            S3ObjectInputStream objectInputStream = s3Object.getObjectContent();

            String jsonString = readJsonFromStream(objectInputStream);

            return objectMapper.readValue(jsonString, Rent.class);
        	
        } catch (IOException e) {
        		throw new StorageCloudException("Erro ao Serializar o objeto", e);
        	} catch (AmazonServiceException e) {
        		throw new StorageCloudException("Erro ao obter o objeto do Amazon S3: ", e);
        	}
    }

	@Override
	public void deleteFromCloud(String fileName) {
	try {
		
		String caminhoArquivo = getFilePath(fileName);
		
		var deleteObjectRequest = new DeleteObjectRequest(storageProperties.getS3().getBucket(), caminhoArquivo);
		
		amazonS3.deleteObject(deleteObjectRequest);
		
		}catch(AmazonServiceException e) {
			throw new StorageCloudException("Não foi possível excluir arquivo na Amazon S3.", e);
		}
	}
		
	private String getFilePath(String fileName) {
		return String.format("%s/%s", storageProperties.getS3().getDirectory(), fileName);
	}

	 private String readJsonFromStream(S3ObjectInputStream objectInputStream) throws IOException {
	        try (BufferedReader reader = new BufferedReader(new InputStreamReader(objectInputStream, StandardCharsets.UTF_8))) {
	            StringBuilder stringBuilder = new StringBuilder();
	            char[] buffer = new char[1024];
	            int bytesRead;

	            while ((bytesRead = reader.read(buffer)) > 0) {
	                stringBuilder.append(buffer, 0, bytesRead);
	            }

	            return stringBuilder.toString();
	        }
	   }
}
