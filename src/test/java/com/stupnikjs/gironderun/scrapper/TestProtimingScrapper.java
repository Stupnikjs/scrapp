package com.stupnikjs.gironderun.scrapper;

import com.stupnikjs.gironderun.model.Course;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// learn Mockito


class TestProtimingScrapper {


     ProtimingScrapper protimingScrapper = new ProtimingScrapper();
     @Test
     @DisplayName("MON PREMIER TEST ")
    public void testScrapper(TestInfo testInfo){
         System.out.println("displayName : " + testInfo.getDisplayName());
         List<Course> courses = protimingScrapper.secondScrapper(2);
         Assertions.assertFalse(courses.isEmpty());

    }

    @Test
    @DisplayName("DEUXIEME TEST")
    public void secondTest(TestInfo testInfo){
        System.out.println("displayName :" + testInfo.getDisplayName());
        List<Course> courses = protimingScrapper.secondScrapper(45);
        Assertions.assertTrue(courses.isEmpty());
    }



}
