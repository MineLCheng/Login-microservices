package com.example.eurekahystrixclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RibbonService {
    @Autowired
    RestTemplate restTemplate;
    public String ribbon(){
        return restTemplate.getForObject("http://eureka-provider/port",String.class);
    }
}

