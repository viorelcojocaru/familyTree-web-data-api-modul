package com.esempla.familyTree.familyTreedata.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "app_account_type")
@RequiredArgsConstructor
@Data
public class AppAccountType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

}
