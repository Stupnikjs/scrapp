package com.stupnikjs.gironderun.repository;

import com.stupnikjs.gironderun.model.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositoryImp {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Course> findAll(){
        String sql = "SELECT c FROM Course AS c";
        Query query = entityManager.createNativeQuery(sql, Course.class);
        return query.getResultList();
    }

    @Transactional
    public void saveCourse(Course course){
        if(!courseAlreadyInDB(course))
            entityManager.persist(course);
    }

    boolean courseAlreadyInDB(Course course){
        String sql = "SELECT c FROM Course AS c WHERE nom = $1 and lieu = $2";
        Query query = entityManager.createNativeQuery(sql , Course.class);
        query.setParameter(1, course.getNom());
        query.setParameter(2, course.getLieu());
        List<Course> courses = query.getResultList();
        if (courses.isEmpty()) return false;
        else return true;
    }

    public void updateSources(Course course, String scrappernom){

    }

}
