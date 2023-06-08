package com.stupnikjs.gironderun.cli;

import com.stupnikjs.gironderun.model.Course;
import com.stupnikjs.gironderun.repository.RepositoryImp;
import com.stupnikjs.gironderun.scrapper.ProtimingScrapper;
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
    RepositoryImp repositoryImp;


    @ShellMethod(key = "scrapp")
    public void scrapp() {
        List<Course> courses = new ArrayList<>();
        for (int i = 0; i < 6; i++)
            courses.addAll(protimingScrapper.secondScrapper(i));

        for (Course course : protimingScrapper.clean(courses)) {
            repositoryImp.saveCourse(course);
        }
    }



/*
    @ShellMethod(key="q")
    public void quit(){

    }

}
*/
}



