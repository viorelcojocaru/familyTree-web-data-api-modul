package com.esempla.familyTree.familyTreedata.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class PersonDto implements Serializable {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @ApiModelProperty(notes = "id of person from data base")
    private Long id;
    @ApiModelProperty(notes = "first name of person from data base")
    private String firstName;
    @ApiModelProperty(notes = "last name of person")
    private String lastName;
    @ApiModelProperty(notes = "last given on birth  of person")
    private String lastNameOnBirth;
    @ApiModelProperty(notes = "gender of person default undefined")
    private int gender;
    @ApiModelProperty(notes = "date of birth of person")
    private Date birthDate;
    @ApiModelProperty(notes = "flag is person in alive , default true")
    private boolean inALive;
    @ApiModelProperty(notes = "date of death of person, default null")
    private Date deathDate;
    @ApiModelProperty(notes = "url photo collections of person")
    private String photoPath;

}
