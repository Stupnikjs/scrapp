package com.stupnikjs.gironderun.model;


import java.util.List;

public class Scrapper {

    String nom;

    public Scrapper(String nom){
    this.nom = nom;
    }

    public String getNom(){
        return this.nom;
    }

    public List<Course> clean(List<Course> courses){
        return courses.stream()
                .filter(course -> !(course.getNom().isEmpty()))
                .toList();
    }

}



