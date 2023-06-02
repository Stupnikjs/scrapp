package com.stupnikjs.gironderun.scrapper;

import com.stupnikjs.gironderun.model.Course;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class ProtimingScrapper {


    public List<Course> secondScrapper(int page) {
        List<Course> courses = new ArrayList<>();
        String uri = String.format("https://protiming.fr/Runnings/liste/page:%d/sort:Running.date/direction:asc?url=Runnings/liste", page);
        try {
            Document doc = Jsoup.connect(uri)
                    .get();
            Elements elements = doc.select(".panel-container");

            for (Element element : elements) {
                String nom;
                List<String> spanArray = Arrays.asList(element.select(".col-xs-12").select("span").text().split(" "));
                Set<String> uniqueLieu = element.select(".col-xs-12").select("p").stream()
                        .map(Element::text)
                        .collect(Collectors.toSet());
                String lieu = uniqueLieu.isEmpty() ? "": uniqueLieu.toArray()[0].toString();
                String[] lieuSplited = lieu.split(" \\(");
                String departement  = lieuSplited.length > 1 ? lieuSplited[1]: "" ;
                departement = departement.substring(0 , departement.length() > 1 ? departement.length() - 1 : 0 );
                int departementInt;
                try{
                    departementInt = Integer.parseInt(departement);
                } catch (NumberFormatException e){
                     departementInt = 165;
                }

                LocalDate date = dateFormateur(element.select("time").text());

                if (spanArray.size() > 2) {
                    List<String> slice = spanArray.subList(0, spanArray.size() - 1);
                    nom = String.join(" ", slice);
                } else {
                    nom = String.join(" ", spanArray);
                }
                courses.add(new Course("distance",lieuSplited[0], nom , date.toString()  , departementInt));
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return courses;
    }



        private LocalDate dateFormateur(String dateStr){

        String[] array =  {"Janv", "Fév", "Mar", "Avr", "Mai", "Juin", "Juil.", "Août", "Sept.","Oct.", "Nov.", "Déc."};
        List<String> frenchMonth = new ArrayList<>(Arrays.asList(array));


        String[] splited = dateStr.split(" ");

        if (splited.length > 1){
            String year = splited[0];
            String month = splited[1];
            String day = splited[splited.length - 1];

            int yearInt;
            int monthInt;
            int dayInt;


            try{
                yearInt = Integer.parseInt(year);
                dayInt = Integer.parseInt(day);


            } catch (NumberFormatException e){
                return LocalDate.of(0, 0, 0);
            }


            if (frenchMonth.contains(month)) {
                monthInt = frenchMonth.indexOf(month);

                return LocalDate.of(yearInt, monthInt + 1, dayInt);
            } else {
                return LocalDate.of(0, 1, 1);
            }

        }
        return LocalDate.of(0, 1, 1);
    }

    public List<Course> clean(List<Course> courses){
        return courses.stream()
                .filter(course -> !(course.getNom().isEmpty()))
                .toList();
    }

}


