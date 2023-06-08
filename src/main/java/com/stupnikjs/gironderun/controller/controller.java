package com.stupnikjs.gironderun.controller;


import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.stupnikjs.gironderun.model.Course;

import com.stupnikjs.gironderun.scrapper.Courrir33Scrapper;
import com.stupnikjs.gironderun.scrapper.ProtimingScrapper;

import com.stupnikjs.gironderun.service.CourseServiceImp;
import com.stupnikjs.gironderun.utils.Geolocate;
import com.stupnikjs.gironderun.utils.HttpUtils;
import com.stupnikjs.gironderun.utils.IpChecker;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"*"})
public class controller {

    @Value("${maxmind.userid}")
    private int userid;

    @Value("${maxmind.password}")
    private String password;

    @Autowired
    ProtimingScrapper protimingScrapper;


    @Autowired
    Courrir33Scrapper courrir33Scrapper;


    @Autowired
    CourseServiceImp courseService;


    @GetMapping("/testheader")
    public List<Course> getHeaders(@RequestHeader Map<String, String> headers){
        headers.forEach((key, value ) -> {
            System.out.println(value);
        });
        return courseService.getAllCourse();
    }


    @GetMapping("/all")
    public List<Course> getAll(){
          return courseService.getAllCourse();
    }

    @GetMapping("/departement/{dep}")
    public List<Course> getByDep(@PathVariable int dep){

        return courseService.getCourseByDep(dep);


    }



    @GetMapping("/ip")
    public String getIp(HttpServletRequest request) throws Exception {

        Geolocate geolocate = new Geolocate();


        String ip = IpChecker.getIp();
        System.out.println("ip : " +  ip);

        String remoteip = HttpUtils.getRequestIP(request);

        String city;
        try{
             city = geolocate.getLocation(userid, password,remoteip);

        } catch (GeoIp2Exception e) {
           e.printStackTrace();
           city = "";
        }
        return city;
    }
}
