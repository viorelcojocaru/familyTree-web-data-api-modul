package com.esempla.familyTree.familyTreewebstarter.service;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Cat extends Animal{
    private String meau;
}
