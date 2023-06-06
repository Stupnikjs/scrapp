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
public class Courrir33Scrapper extends Scrapper {


    public Courrir33Scrapper(){
        super("Courrir33Scrapper");

    }

    public List<Course> Scrapper() {
        List<Course> courses = new ArrayList<>();
        String date = "";
        String type = "";
        String nom = "";
        String lieu = "";
        List<String> distance = new ArrayList<>();

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
                            distance.add((elts.get(i).text()));
                    }

                }
                if (!(nom.isEmpty() && lieu.isEmpty())) {
                    Course newCourse = new Course(lieu, nom, date, 33, this.getNom());
                    newCourse.setDistance(distance);
                    courses.add(newCourse);
                }
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
