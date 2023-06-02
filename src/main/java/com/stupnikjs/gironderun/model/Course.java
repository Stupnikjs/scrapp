package com.stupnikjs.gironderun.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;



public class Course {
    @JsonProperty("distance")
    String distance;

    @JsonProperty("date")
    private Date date;

    @JsonProperty("lieu")
    String lieu;

    @JsonProperty("departement")
    int departement;

    public String getNom() {
        return nom;
    }


    @JsonProperty("nom")
    String nom;
    public Course(){

    }


    public Date getDate() {
        return date;
    }

    public Course(String distance, String lieu, String nom, String date, int departement) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.distance = CapitalizeFirstLetter(distance);
        this.lieu = CapitalizeFirstLetter(lieu);
        this.nom = CapitalizeFirstLetter(nom);
        this.departement = departement;

        try{
            Date newdate =  new Date(dateFormat.parse(date).getTime());
            this.date = newdate;

        } catch (ParseException parseException){
            // parseException.printStackTrace();
            this.date = new Date(0);
        }

    }

    private String CapitalizeFirstLetter(String input){
        if (input == null || input.isEmpty()){
            return input;
        }
        return input.substring(0,1).toUpperCase() + input.substring(1).toLowerCase();
    }
}
