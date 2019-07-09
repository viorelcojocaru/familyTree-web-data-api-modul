package com.esempla.familyTree.mongodb.domain;


import lombok.Data;

import java.util.Date;

@Data
public class Person {
    private Long id;
    private String firstName;
    private String lastName;
    private String lastNameOnBirth;
    private int gender;
    private Date birthDate;
    private boolean inALive;
    private Date deathDate;
    private String photoPath;
}
