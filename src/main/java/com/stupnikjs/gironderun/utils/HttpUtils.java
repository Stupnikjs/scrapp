package com.stupnikjs.gironderun.utils;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Enumeration;

public class HttpUtils {
    private static final String[] IP_HEADERS = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR"

            // you can add more matching headers here ...
    };

    private HttpUtils() {
        // nothing here ...
    }

    public static String getRequestIP(HttpServletRequest request) {

        for (String header:IP_HEADERS){
            String ip_header = request.getHeader(header);
            if (ip_header != null ) System.out.println(ip_header.split( "\\s*, \\s*"));

        }
        System.out.println(request.getRemoteAddr());
        return request.getRemoteAddr();
    }
}

