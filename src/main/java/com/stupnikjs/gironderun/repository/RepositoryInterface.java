package com.stupnikjs.gironderun.repository;

import com.stupnikjs.gironderun.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositoryInterface  {

    List<Course> findAll();
    void updateCourse(Course course);

    void deleteCourse(Long id);

    List<Course> findByDepartement(int departement);

    boolean courseAlreadyInDB(Course course);

    public void updateSources(Course course, String scrappernom)

    void saveCourse(Course course);

}
