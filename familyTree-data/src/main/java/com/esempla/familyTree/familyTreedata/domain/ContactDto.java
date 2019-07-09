package com.esempla.familyTree.familyTreedata.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
public class ContactDto {

    private Long id;
    @DBRef(db = "personDto")
    private Long personId;

    private String email;

    private String webSite;

    private String faceBook;

    private String blog;

    private String photoGalery;

    private String skype;

    private String city;
    @DBRef(db = "countryDto")
    private Long countryId;

}
