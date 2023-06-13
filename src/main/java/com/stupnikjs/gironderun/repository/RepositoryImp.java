package com.stupnikjs.gironderun.repository;

import com.stupnikjs.gironderun.model.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
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
        entityManager.persist(course);
    }

    public List<Course> getCourseByDep(int dep){
        String sql = "SELECT * FROM Course WHERE departement = :dep ; ";
        Query query = entityManager.createNativeQuery(sql, Course.class);
        query.setParameter("dep",dep);
        return query.getResultList();

    }

    @Transactional
    public void deleteAll(){
        try{
            String sql = "TRUNCATE TABLE course";
            Query query = entityManager.createNativeQuery(sql, Course.class);
            query.executeUpdate();
        } catch(Exception e){
            e.printStackTrace();
        }

    }


    public int courseCount(String name){
        String sql = "SELECT * FROM Course WHERE nom = :nom";
        Query query = entityManager.createNativeQuery(sql, Course.class);
        query.setParameter("nom", name);
        return query.getResultList().size();

    }

    @Transactional
    public void removeDuplicate(){
        String sql = "DELETE FROM course WHERE id NOT IN (SELECT MIN(id) FROM course GROUP BY nom); ";
        Query query = entityManager.createNativeQuery(sql, Course.class);
        query.executeUpdate();
    }



}
