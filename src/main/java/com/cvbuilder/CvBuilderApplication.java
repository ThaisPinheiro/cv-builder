package com.cvbuilder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CvBuilderApplication {

	public static void main(String[] args) { SpringApplication.run(CvBuilderApplication.class, args); }
	@GetMapping("/auth/login")
	public String index() {
		System.out.println("Deu certo");
		return "Hello World! Quebrando a maldição. Garantindo atualização";
	}
}