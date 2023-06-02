package com.stupnikjs.gironderun.controller;

import com.stupnikjs.gironderun.model.Course;

import com.stupnikjs.gironderun.scrapper.Courrir33Scrapper;
import com.stupnikjs.gironderun.scrapper.ProtimingScrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
public class controller {

    @Autowired
    Courrir33Scrapper first;

    @Autowired
    ProtimingScrapper scrapper;
    @GetMapping("/{page}")
    public  List<Course> controller(@PathVariable int page) {

        List<Course> courses = new ArrayList<>();

        for (int i = 0 ; i < 6; i++){
            courses.addAll(scrapper.secondScrapper(i));
        }

        courses.addAll(first.Scrapper());
        return scrapper.clean(courses);

    }


}
