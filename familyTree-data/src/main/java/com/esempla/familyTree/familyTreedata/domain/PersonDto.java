package com.esempla.familyTree.familyTreedata.domain;

import lombok.Data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class PersonDto implements Serializable {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

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
