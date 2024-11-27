package de.sp.trashNothing_backend;

import de.sp.trashNothing_backend.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class TrashNothingBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrashNothingBackendApplication.class, args);
	}

}
