package com.esempla.familyTree.familyTreedata.domain;

import com.esempla.familyTree.familyTreedata.utill.JsonDateDeserializer;
import com.esempla.familyTree.familyTreedata.utill.JsonDateSerializer;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "person", schema = "public")
@ToString(exclude = {"contact", "appAccount"})
@EqualsAndHashCode(exclude = {"contact", "appAccount"})
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "last_name_on_birth")
    private String lastNameOnBirth;

    private int gender = 30;
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @JsonSerialize(using = JsonDateSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "in_alive")
    private boolean inALive;

    @JsonDeserialize(using = JsonDateDeserializer.class)
    @JsonSerialize(using = JsonDateSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "death_date")

    private Date deathDate;
    @Column(name = "photo_path")

    private String photoPath;

    @OneToOne(mappedBy = "person")
    @JsonManagedReference
    private Contact contact;

    @OneToOne(mappedBy = "person")
    @JsonManagedReference
    private AppAccount appAccount;

}
