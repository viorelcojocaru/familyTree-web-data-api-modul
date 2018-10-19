package com.esempla.familyTree.familyTreewebstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.esempla"})
@SpringBootApplication
public class FamilyTreeWebStarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(FamilyTreeWebStarterApplication.class, args);
	}
}
