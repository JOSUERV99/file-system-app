package com.operatingsystems.filesystemapp;

import com.operatingsystems.filesystemapp.handler.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * FileSystemAppApplication
 * where Spring initializes the application and run the program using the port defined in resources/application.properties
 */
@SpringBootApplication
public class FileSystemAppApplication {

	public static void main(String[] args) {
		FileSystemAppApplication.initFileSystemApp();
		SpringApplication.run(FileSystemAppApplication.class, args);
	}

	public static void initFileSystemApp() {
		FileUtils.initializeFileSystemDirs();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api").allowedOrigins("http://localhost:3000");
			}
		};
	}

}
