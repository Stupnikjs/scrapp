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
        String nom = "";
        String lieu = "";
        List<String> distance = new ArrayList<>();

        try {
            Document doc = Jsoup.connect("http://www.courir33.net/calgeneral23.php").get();
            Element element = doc.select("table").get(3);

            Elements elmts = element.select("tr");
            // suprimer les deux premiers elements

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
                    System.out.println(date);
                    System.out.println(lieu);
                    System.out.println(nom);

                }
                if (!(nom.isEmpty() && lieu.isEmpty())) {
                    // checker si la course est deja enregistrée
                    Course newCourse = new Course(lieu, nom, date,  33, this.getNom());
                    newCourse.setDistance(distance);
                    if (courses.stream().anyMatch(course -> course.getNom().equals(newCourse.getNom()))) {
                        int last = courses.size() - 1;
                        Course course = courses.get(last);
                        course.setDistance(newCourse.getDistance());
                    } else courses.add(newCourse);
                }
            }


        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return courses;
    }




}
