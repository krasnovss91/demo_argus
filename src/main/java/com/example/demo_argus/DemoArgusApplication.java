package com.example.demo_argus;

import com.example.demo_argus.controller.ArgusFitoController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoArgusApplication {


	public static void main(String[] args) {
		SpringApplication.run(DemoArgusApplication.class, args);

		System.out.println();
	}

}
