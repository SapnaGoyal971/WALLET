package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Timestamp;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
	/*	Timestamp timestamp= new Timestamp(System.currentTimeMillis());
		System.out.println(timestamp);*/
		SpringApplication.run(DemoApplication.class, args);
	}


}
