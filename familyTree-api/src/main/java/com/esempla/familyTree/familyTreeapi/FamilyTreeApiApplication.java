package com.esempla.familyTree.familyTreeapi;

import com.esempla.familyTree.familyTreeapi.controller.jws.utils.JWSUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class FamilyTreeApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(com.esempla.familyTree.familyTreeapi.FamilyTreeApiApplication.class, args);
    }

    @Bean
    public JWSUtil jwsUtil() {
        return new JWSUtil();
    }
}
