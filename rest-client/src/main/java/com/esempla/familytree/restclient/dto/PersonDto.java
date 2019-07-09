package com.esempla.familytree.restclient.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class PersonDto {
    @ApiModelProperty(example = "0", notes = "id of person from data base")
    private Long id;
    @ApiModelProperty(example = "Viorel", notes = "first name of person from data base")
    private String firstName;
    @ApiModelProperty(example = "Cojocaru", notes = "last name of person")
    private String lastName;
    @ApiModelProperty(example = "Cojocaru", notes = "last given on birth  of person")
    private String lastNameOnBirth;
    @ApiModelProperty(example = "30", notes = "gender of person default undefined")
    private int gender;
    @ApiModelProperty(example = "1984-23-01",notes = "date of birth of person")
    private Date birthDate;
    @ApiModelProperty(example = "true", notes = "flag is person in alive , default true")
    private boolean inALive;
    @ApiModelProperty(notes = "date of death of person, default null")
    private Date deathDate;
    @ApiModelProperty(example = "https://www.google.com/url?sa=i&source=images&cd=&cad=rja&uact=8&ved=2ahUKEwj-nqXXjcLeAhUCCuwKHa9LCd4QjRx6BAgBEAU&url=https%3A%2F%2Fcontactout.com%2Fviorel-cojocaru-6554943&psig=AOvVaw205WLJsPnD4NS9rIgs4m1y&ust=1541674086880617", notes = "url photo collections of person")
    private String photoPath;
}
