package com.operatingsystems.filesystemapp;

import com.operatingsystems.filesystemapp.handler.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

}
