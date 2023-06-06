package com.stupnikjs.gironderun.controller;

import com.stupnikjs.gironderun.model.Course;

import com.stupnikjs.gironderun.scrapper.Courrir33Scrapper;
import com.stupnikjs.gironderun.scrapper.ProtimingScrapper;

import com.stupnikjs.gironderun.service.CourseServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = {"*"})
public class controller {

    @Autowired
    Courrir33Scrapper first;

    @Autowired
    ProtimingScrapper scrapper;


    @Autowired
    CourseServiceImp courseService;

    @GetMapping("/scrapp/{page}")
    public  List<Course> controller(@PathVariable int page) {

        List<Course> courses = new ArrayList<>();

        /*
        for (int i = 0 ; i < 6; i++){
            courses.addAll(scrapper.secondScrapper(i));
        }
        */

        courses.addAll(scrapper.secondScrapper(page));

        Course newCourse;

        for (Course course:scrapper.clean(courses)){
            newCourse  = scrapper.GetDetails(course);
            courseService.saveCourse(newCourse);
        }



        return scrapper.clean(courses);
    }

    @GetMapping("/all")
    public List<Course> getAll(){
        return courseService.getAllCourse();
    }

}
