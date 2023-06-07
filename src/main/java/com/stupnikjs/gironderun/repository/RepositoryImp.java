package com.stupnikjs.gironderun.repository;

import com.stupnikjs.gironderun.model.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositoryImp implements RepositoryInterface {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Course> findAll(){
        String sql = "SELECT * FROM Course";
        Query query = entityManager.createNativeQuery(sql, Course.class);
        return query.getResultList();
    }

    @Transactional
    public void saveCourse(Course course){
        /* boolean alreadyInDb = courseAlreadyInDB(course);
        if(!alreadyInDb)*/
        entityManager.persist(course);

        /*else {
            String sql = "INSERT INTO Course(sources) values (ARRAY[?])";
            Query query = entityManager.createNativeQuery(sql , Course.class);
            query.setParameter(1, scrappernom);
            query.executeUpdate();

        }*/
    }


    public boolean courseAlreadyInDB(Course course){
        String sql = "SELECT * FROM Course WHERE nom = :nom and lieu = :lieu";
        Query query = entityManager.createNativeQuery(sql , Course.class);
        query.setParameter("nom", course.getNom());
        query.setParameter("lieu", course.getLieu());
        List<Course> courses = query.getResultList();
        return !courses.isEmpty();
    }


}
