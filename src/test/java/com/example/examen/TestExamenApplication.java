package com.example.examen;

import org.springframework.boot.SpringApplication;

public class TestExamenApplication {

	public static void main(String[] args) {
		SpringApplication.from(ExamenApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
