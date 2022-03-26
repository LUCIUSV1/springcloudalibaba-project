package org.lucius.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MainController {

    @Value("${url}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/index")
    public String index(){

        return  restTemplate.getForObject(url+"/index",String.class);
    }
}
