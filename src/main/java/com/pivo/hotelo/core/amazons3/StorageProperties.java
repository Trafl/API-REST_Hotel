package com.pivo.hotelo.core.amazons3;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.amazonaws.regions.Regions;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties("hotelo")
@Getter
@Setter
@Component
public class StorageProperties {

	private S3 s3 = new S3();
	
	@Setter
	@Getter
	public class S3 {
		
		private String acessKey;
		private String secretKey;
		private String bucket;
		private Regions region;
		private String directory;
	}
	
}
