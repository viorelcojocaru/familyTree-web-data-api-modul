package com.esempla.familyTree.familyTreewebstarter.web;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ExportDestination {
    private String name;
    private String description;
    private MultipartFile file;

}
