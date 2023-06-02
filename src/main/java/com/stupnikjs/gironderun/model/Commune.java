package com.stupnikjs.gironderun.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Commune {

    @JsonProperty("nom")
    private String nom;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}
