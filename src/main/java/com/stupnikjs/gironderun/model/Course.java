package com.stupnikjs.gironderun.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @JsonProperty("distance")
    List<String> distance;

    @JsonProperty("date")
    private Date date;

    @JsonProperty("lieu")
    String lieu;

    @JsonProperty("departement")
    int departement;

    public String getNom() {
        return nom;
    }


    public String getLieu() {
        return lieu;
    }

    @JsonProperty("nom")
    String nom;

    List<String> sources ;

    public List<String> getSources() {
        return sources;
    }

    public void appendSource(Scrapper scrapper) {
        this.sources.add(scrapper.nom);
    }

    public List<String> getDistance() {
        return distance;
    }

    public void setDistance(List<String> distance) {
        this.distance = distance;
    }

    @JsonProperty("link")
    String link;

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public Course(){

    }


    public Date getDate() {
        return date;
    }

    public Course( String lieu, String nom, String date, int departement, String scrapperNom) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.lieu = CapitalizeFirstLetter(lieu);
        this.nom = CapitalizeFirstLetter(nom);
        this.departement = departement;
        this.sources.add(scrapperNom);

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
