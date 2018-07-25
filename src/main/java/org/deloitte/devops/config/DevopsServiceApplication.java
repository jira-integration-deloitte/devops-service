package org.deloitte.devops.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="org.deloitte")
public class DevopsServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(DevopsServiceApplication.class, args);
	}
}
