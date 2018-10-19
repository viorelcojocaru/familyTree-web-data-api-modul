package com.esempla.familyTree.familyTreeapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.esempla"})
public class FamilyTreeApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(com.esempla.familyTree.familyTreeapi.FamilyTreeApiApplication.class, args);
    }
}
