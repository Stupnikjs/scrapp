package com.stupnikjs.gironderun.scrapper;

import com.stupnikjs.gironderun.model.Course;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class Courrir33Scrapper {
    public List<Course> Scrapper() {
        List<Course> courses = new ArrayList<>();
        String date = "";
        String type = "";
        String nom = "";
        String lieu = "";
        String distance = "";

        try {
            Document doc = Jsoup.connect("http://www.courir33.net/calgeneral23.php").get();
            Element element = doc.select("table").get(3);

            Elements elmts = element.select("tr");


            for (Element elt : elmts) {
                Elements elts = elt.select("td");
                for (int i = 0; i < elts.size(); i++) {
                    switch (i) {
                        case 0:
                            date = elts.get(i).text();
                        case 1:
                            lieu = elts.get(i).text();
                        case 2:
                            nom = elts.get(i).text();
                        case 3:
                            distance = elts.get(i).text();
                    }

                }
                if (!(nom.isEmpty() && lieu.isEmpty() && distance.isBlank()))
                    courses.add(new Course(distance, lieu, nom, date, 33));
            }


        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return courses;
    }

    private  List<Course> removeTrash(List<Course> courses){
        
        return courses;
    }


}
