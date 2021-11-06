package com.operatingsystems.filesystemapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * FileSystemAppApplication
 * where Spring initializes the application and run the program using the port defined in resources/application.properties
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan()
public class FileSystemAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileSystemAppApplication.class, args);
	}

}
