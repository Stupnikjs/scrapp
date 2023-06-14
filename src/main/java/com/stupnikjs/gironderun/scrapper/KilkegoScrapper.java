package com.stupnikjs.gironderun.scrapper;

import com.stupnikjs.gironderun.model.Course;
import com.stupnikjs.gironderun.model.Scrapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Connection;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.jsoup.select.Selector.select;

@Component
public class KilkegoScrapper extends Scrapper {

    public KilkegoScrapper() {
        super("Kilkego");
    }


    public void scrapper(int page) {
        List<Course> courses = new ArrayList<>();


        try {
            // to get the cookies
            String uri = String.format("https://klikego.com/my/calendrierv6.jsp?page=%dmonth-index=", page);
            Connection.Response response = Jsoup.connect(uri)
                    .method(Connection.Method.GET)
                    .execute();

            System.out.println(response.cookies());
            Document doc = Jsoup.connect(uri)
                    .cookies(response.cookies())
                    .get();

            System.out.println(doc.select(".card").text());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
/*
        public Course processElement(Element element){


        String nom = element.select(".texte-vert-fonce").text().toLowerCase();
        String lieu = element.select(".texte-localisation").text();
        int departement = extractDepartement(lieu);
        lieu = lieu.split("\\(")[0];
        String[] date;

        if (element.select(".badge-pill").select("b").text().split(" ").length <= 1)
            date = element.select(".badge-pill").select("b").text().split("/");
        else date = element.select(".badge-pill").select("b").text().split(" ")[0].split("/");

        try {
            int day = Integer.parseInt(date[0]);
            int month = Integer.parseInt(date[1]);
            LocalDate dateF = LocalDate.of(2023, month, day);
            System.out.println(dateF.toString());

            Course newCourse = new Course(lieu,nom,dateF.toString(), departement,this.getNom());
            return newCourse;

        } catch (NumberFormatException e){
            e.printStackTrace();
            return null ;
        }
    }*/
}
