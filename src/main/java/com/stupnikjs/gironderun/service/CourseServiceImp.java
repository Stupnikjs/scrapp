package com.stupnikjs.gironderun.service;

import com.stupnikjs.gironderun.model.Course;
import com.stupnikjs.gironderun.repository.RepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImp implements CourseServiceInterface{

    @Autowired
    RepositoryInterface repo ;


    public List<Course> getAllCourse(){
        return repo.findAll();
    }



    public void saveCourse(Course course, String scrapperNom){
        repo.saveCourse(course);
    }


    public List<Course> getCourseByDep(int dep) {
        return repo.getCourseByDep(dep);
    }
}
