package com.esempla.familyTree.familyTreewebstarter.service;

import java.util.ArrayList;
import java.util.List;

public class Testclazzz {
    public static void main(String[] args) {
//        Dog dog =new Dog();
//        dog.setFamilie("cojocaru");
//        dog.setName("viorel");
//        System.out.println(dog.toString());
        Animal animal= new Dog();

        List<Animal> animale=new ArrayList<>();
        animale.add(new Dog());
//        animale.add(new Cat());
        animale.forEach(one-> System.out.println((Dog)one));

    }
}
