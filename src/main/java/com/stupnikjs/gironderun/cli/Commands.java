package com.stupnikjs.gironderun.cli;

import com.stupnikjs.gironderun.model.Course;
import com.stupnikjs.gironderun.repository.RepositoryImp;
import com.stupnikjs.gironderun.scrapper.KilkegoScrapper;
import com.stupnikjs.gironderun.scrapper.ProtimingScrapper;
import com.stupnikjs.gironderun.scrapper.RunTrailScrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.ArrayList;
import java.util.List;

// built in command exit => quit


@ShellComponent
public class Commands {


    @Autowired
    ProtimingScrapper protimingScrapper;

    @Autowired
    KilkegoScrapper kilkegoScrapper;

    @Autowired
    RepositoryImp repositoryImp;


    @ShellMethod(key = "scrapp protiming")
    public void scrappProtiming() {
        List<Course> courses = new ArrayList<>();
        for (int i = 0; i < 6; i++)
            courses.addAll(protimingScrapper.secondScrapper(i));

        for (Course course : protimingScrapper.clean(courses)) {
            repositoryImp.saveCourse(course);
        }
    }

/*
    @ShellMethod(key= "scrapp runtrail")
    public void scrappRunTrail() {
        List<Course> courses;

        for (int i = 1; i < 18; i++) {
            courses = runTrailScrapper.scrapper(i);

            for (Course course : runTrailScrapper.clean(courses)) {
                if (courseCount(course.getNom()) == 0)
                    repositoryImp.saveCourse(course);
            }
        }
    }
*/
    @ShellMethod(key= "scrapp kilkego")
    public void scrappRunTrail(int page) {
        List<Course> courses;
        kilkegoScrapper.scrapper(page);

    }



    @ShellMethod(key="delete all")
    public void deleteAll(){
        repositoryImp.deleteAll();
    }

    @ShellMethod(key = "count")
    public int courseCount(String name){
    return repositoryImp.courseCount(name);
    }


    @ShellMethod(key = "clean")
    public void cleanDuplicate(){
        repositoryImp.removeDuplicate();
    }

}



