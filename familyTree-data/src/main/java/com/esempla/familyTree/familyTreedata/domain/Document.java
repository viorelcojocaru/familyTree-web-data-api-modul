package com.esempla.familyTree.familyTreedata.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
public class Document {
    @Id
    private String id;

    private String subject;
    private String date;

    @DBRef
    private FileUpload file;

}
