package com.stupnikjs.gironderun.service;


import org.springframework.stereotype.Service;
import com.stupnikjs.gironderun.model.Course;

import java.util.List;


public interface CourseServiceInterface {

    public List<Course> getAllCourse();

    public void saveCourse(Course course);


}
