package com.stupnikjs.gironderun.scrapper;

import com.stupnikjs.gironderun.model.Course;
import com.stupnikjs.gironderun.model.Scrapper;
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
public class ProtimingScrapper extends Scrapper {




    public ProtimingScrapper(){
        super("Protiming");

    }

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
                String link = element.select(".panel-container").attr("id");

                if (link.length() > 3){
                    link = "https://protiming.fr/Runnings/detail/" + link.substring(3);
                }




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

                LocalDate date = dateFormateur(element.select("time").text(), 0);

                if (spanArray.size() > 2) {
                    List<String> slice = spanArray.subList(0, spanArray.size() - 1);
                    nom = String.join(" ", slice);
                } else {
                    nom = String.join(" ", spanArray);
                }

                Course newCourse = new Course(lieuSplited[0], nom , date.toString() , departementInt, this.getNom());
                newCourse.setLink(link);


                courses.add(newCourse);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return courses;
    }








    public Course GetDetails(Course course){

        if(course.getLink() != ""){
         try {
             Document doc = Jsoup.connect(course.getLink()).get();
             Elements eltRow = doc.select(".panel-body").select("tr");

             List<String> dist = new ArrayList<>();
             String distItem;
             int distItemInt;

             for(Element el:eltRow) {

                 String[] splitedItem = el.text().split("km");

                 for (int i = 0 ; i < splitedItem.length ; i++ ){

                     distItem = el.text().split("km")[i];
                     try {
                         distItemInt = Integer.parseInt(distItem);
                         dist.add(distItem + "km");


                     } catch (NumberFormatException e){

                     }
                 }

                 Set<String> uniqueDist = dist.stream()
                         .collect(Collectors.toSet());

                 List<String> uniqueDistList = uniqueDist.stream().toList();
                 course.setDistance(uniqueDistList);
             }

         } catch (IOException e){

         }

            return course ;
        } else return null ;


    }

}


