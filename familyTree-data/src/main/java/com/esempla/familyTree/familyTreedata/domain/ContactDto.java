package com.esempla.familyTree.familyTreedata.domain;

import lombok.Data;

@Data
public class ContactDto {

    private Long id;

    private Long personId;

    private String email;

    private String webSite;

    private String faceBook;

    private String blog;

    private String photoGalery;

    private String skype;

    private String city;

    private Long countryId;

}
