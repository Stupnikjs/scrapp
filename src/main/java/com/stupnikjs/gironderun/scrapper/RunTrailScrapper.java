package com.stupnikjs.gironderun.scrapper;

import com.stupnikjs.gironderun.model.Course;
import com.stupnikjs.gironderun.model.Scrapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class RunTrailScrapper extends Scrapper {

    public RunTrailScrapper(){
        super("Runtrail");
    }

    public List<Course> scrapper(int page) {

        List<Course> courses = new ArrayList<>();
        String uri;
        if (page != 0 ) uri = String.format("https://www.runtrail.fr/events/running?page=%d",page );
        else uri = "https://www.runtrail.fr/events/running";

        try {
            Document doc = Jsoup.connect(uri).get();
            Elements elements = doc.select(".event-item");


            for (Element element : elements) {

                String url = element.select("figure").select("a").eachAttr("href").get(0);
                System.out.println(url);
                String link;
                try{
                    String detailUrl = String.format("https://runtrail.fr%s", url);
                    Document docDetail = Jsoup.connect(detailUrl).get();
                    link = docDetail.select("#event-features").select("a").eachAttr("href").get(0);
                    link = String.format("https://runtrail.fr%s", link);

                } catch (IOException e){
                    link = "";
                }

                String nom = element.select(".text-dark").text();
                String date = element.select(".wrapper").select("div").get(2).text();
                String lieu = element.select(".wrapper").select("span").text();
                String departement = lieu.split("\\(").length > 1 ? lieu.split("\\(")[1] : "";
                lieu = lieu.split("\\(")[0];
                departement = departement.split("\\)")[0];

                Elements distancesElmt = element.select("ul");
                List<String> distances = new ArrayList<>();
                for(Element elt:distancesElmt){
                    distances.add(elt.text());
                }
                int depInt;
                try{
                    depInt = Integer.parseInt(departement);
                } catch (NumberFormatException n){
                    depInt = 0;
                }
                if (depInt != 0) {
                    if(date.split(" ").length < 3) date = "01 " + date ;
                    date = String.valueOf(dateFormateur(date, 1));

                    Course newCourse = new Course(lieu, nom,date, depInt, this.getNom());
                    newCourse.setLink(link);
                    courses.add(newCourse);
                }


            }

        } catch (IOException e) {

        }
        return courses;
    }

}
