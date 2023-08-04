package com.pivo.hotelo.core.amazons3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class CloudStoreConfig {

	@Autowired
	private StorageProperties storageProperties;
	
	@Bean
	public AmazonS3 amazonS3() {
		
		var credencias = new BasicAWSCredentials(storageProperties.getS3().getAcessKey(), storageProperties.getS3().getSecretKey());
		
		AmazonS3 amazonS3Cliente =  AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credencias))
				.withRegion(storageProperties.getS3().getRegion())
				.build();
		
		return amazonS3Cliente;
	}
}
