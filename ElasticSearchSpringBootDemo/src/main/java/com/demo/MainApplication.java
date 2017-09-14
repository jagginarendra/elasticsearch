package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication {

	public MainApplication() {
		
		System.out.println("Hello World");
	}
	
	
	public static void main(String[] args) {
		
		SpringApplication.run(MainApplication.class, args);
	}
}
