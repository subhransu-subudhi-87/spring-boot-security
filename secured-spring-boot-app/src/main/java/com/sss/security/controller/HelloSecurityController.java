package com.sss.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloSecurityController {

	@GetMapping("/sayHello")
	public String sayHello() {
		return "Hello from Spring-Security-App";
	}
}
