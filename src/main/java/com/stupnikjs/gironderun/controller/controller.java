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
@RequestMapping("/api")
@CrossOrigin(origins = {"*"})
public class controller {


    @Autowired
    ProtimingScrapper protimingScrapper;


    @Autowired
    Courrir33Scrapper courrir33Scrapper;


    @Autowired
    CourseServiceImp courseService;

    @GetMapping("/scrapp/{page}")
    public  List<Course> protimingController (@PathVariable int page) {

        List<Course> courses = new ArrayList<>();

        /*
        for (int i = 0 ; i < 6; i++){
            courses.addAll(scrapper.secondScrapper(i));
        }
        */

        courses.addAll(protimingScrapper.secondScrapper(page));

        Course newCourse;

        for (Course course:protimingScrapper.clean(courses)){
            newCourse  = protimingScrapper.GetDetails(course);
            courseService.saveCourse(newCourse, protimingScrapper.getNom());
        }



        return protimingScrapper.clean(courses);
    }


    /* pas fonctionnel
    @GetMapping("/scrapp/courrir33/")
    public List<Course> protimingController(){
        List<Course> courses = new ArrayList<>();

        courses.addAll(courrir33Scrapper.Scrapper());
        return courrir33Scrapper.clean(courses);

    }
   */

    @GetMapping("/testheader")
    public List<Course> getHeaders(@RequestHeader Map<String, String> headers){
        headers.forEach((key, value ) -> {
            System.out.println(value);
        });
        return courseService.getAllCourse();
    }


    @GetMapping("/all")
    public List<Course> getAll(){
          return courseService.getAllCourse();
    }



}
