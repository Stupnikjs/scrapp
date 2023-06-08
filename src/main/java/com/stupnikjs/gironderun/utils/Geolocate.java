package com.stupnikjs.gironderun.utils;

import com.maxmind.geoip2.WebServiceClient;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.Country;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class Geolocate {





    public Geolocate() {
    }

    public String getLocation(int userid, String password, String ip) throws IOException, GeoIp2Exception {
        WebServiceClient client = new WebServiceClient.Builder(userid, password).host("geolite.info").build();


        InetAddress ipAddress = InetAddress.getByName(ip);

        CityResponse response = client.city(ipAddress);

        return response.getCity().getName();

    }
}
