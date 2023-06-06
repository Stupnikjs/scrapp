package com.stupnikjs.gironderun.controller;

import com.stupnikjs.gironderun.model.Course;

import com.stupnikjs.gironderun.scrapper.Courrir33Scrapper;
import com.stupnikjs.gironderun.scrapper.ProtimingScrapper;

import com.stupnikjs.gironderun.service.CourseServiceImp;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.net.http.HttpHeaders;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/testheader")
    public List<Course> getHeaders(@RequestHeader Map<String, String> headers){
        headers.forEach((key, value ) -> {
            System.out.println(value);
        });
        return courseService.getAllCourse();
    }


    @GetMapping("/all")
    public List<Course> getAll(@RequestHeader Map<String, String> headers){
        headers.forEach((key, value ) -> {
            System.out.println(value);
        });
        return courseService.getAllCourse();
    }



}
