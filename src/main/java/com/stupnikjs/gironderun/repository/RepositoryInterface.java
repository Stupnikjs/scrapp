package com.stupnikjs.gironderun.repository;

import com.stupnikjs.gironderun.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositoryInterface  {

    List<Course> findAll();




    List<Course> getCourseByDep(int departement);




    void deleteAll();

    void saveCourse(Course course);

}
