package com.stupnikjs.gironderun.model;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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

    public LocalDate dateFormateur(String dateStr, int ScrapperIndex){

        System.out.println(dateStr);
        String[] array = new String[]{"Janv", "Fév", "Mar", "Avr", "Mai", "Juin", "Juil.", "Août", "Sept.", "Oct.", "Nov.", "Déc."};
        String[] array1 = new String[]{"janvier", "février", "mars", "avril", "mai", "juin", "juillet", "août", "septembre", "octobre", "novembre", "décembre"};


        if (ScrapperIndex == 1) array = array1;

        List<String> frenchMonth = new ArrayList<>(Arrays.asList(array));
        String[] splited = dateStr.split(" ");

        if (splited.length > 2){
            String year;
            String month;
            String day;

            if(ScrapperIndex == 0) {
                 year = splited[0];
                 month = splited[1];
                 day = splited[splited.length - 1];
            } else  { // Scrapper index == 1
                System.out.println();
                if (splited.length < 4) {
                    year = splited[splited.length - 1];
                    month = splited[1];
                    day = splited[0];
                } else {

                    year = splited[splited.length - 1];
                    month = splited[splited.length - 2];
                    day = splited[splited.length - 5];
                }
            }



            int yearInt;
            int monthInt;
            int dayInt;

            try{
                yearInt = Integer.parseInt(year);
                dayInt = Integer.parseInt(day);
            } catch (NumberFormatException e){
                return LocalDate.of(0, 0, 0);
            }

            if (frenchMonth.contains(month.toLowerCase())) {
                monthInt = frenchMonth.indexOf(month.toLowerCase());
                System.out.println(monthInt);

                return LocalDate.of(yearInt, monthInt + 1, dayInt);
            } else {
                return LocalDate.of(0, 1, 1);
            }

        }
        return LocalDate.of(0, 1, 1);
    }
}



