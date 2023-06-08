package com.stupnikjs.gironderun.repository;

import com.stupnikjs.gironderun.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositoryInterface  {

    List<Course> findAll();


    /*void updateCourse(Course course);

    void deleteCourse(Long id);


     */

    List<Course> getCourseByDep(int departement);




    // public boolean courseAlreadyInDB(Course course);



    void saveCourse(Course course);

}
