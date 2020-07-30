package com.junttest.SBJunit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//this is full example of Junit test case for spring boot
@SpringBootApplication
public class SbJunitApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbJunitApplication.class, args);
		
		System.out.println("**********Application Started");
	}

}
