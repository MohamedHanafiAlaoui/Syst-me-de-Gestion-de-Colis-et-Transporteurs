package com.example.Transporteurs;

import org.springframework.boot.SpringApplication;

public class TestTransporteursApplication {

	public static void main(String[] args) {
		SpringApplication.from(TransporteursApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
