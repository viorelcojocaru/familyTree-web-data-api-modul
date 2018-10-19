package com.esempla.familyTree.familyTreedata.domain;


import lombok.*;

import javax.persistence.*;

@Data
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Entity
@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private int id;

    private String name;
}
