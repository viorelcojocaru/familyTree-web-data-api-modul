package com.esempla.familytree.restclient.controller;

import com.esempla.familytree.restclient.config.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url = "localhost:8080/api/jws/person", name = "person", configuration = FeignClientConfiguration.class)
public interface PersonClient {

    @GetMapping("/list")
    public String getPersons();

    @PostMapping("/create")
    public String savePersonOnServer(String encodedJson);
}
