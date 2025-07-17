package com.yean.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

// Annotation
@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	// Get Request - Retrieve record from server
	// Api endpoint : http://localhost:8080/api/v1/hello
//	@RequestMapping(value = "/api/v1/hello" , method = RequestMethod.GET)

}
