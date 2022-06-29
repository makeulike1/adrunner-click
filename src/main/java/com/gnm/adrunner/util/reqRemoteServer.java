package com.gnm.adrunner.util;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class reqRemoteServer {

    public static ResponseEntity<String> requestGET(String URL){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(URL, String.class);
        return response;
    }

}
