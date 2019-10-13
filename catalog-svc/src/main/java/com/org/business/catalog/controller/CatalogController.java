package com.org.business.catalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RefreshScope
@RestController
//@RequestMapping("/api")
@RequestMapping("/inventory")
public class CatalogController {

    @Autowired
    RestTemplate restTemplate;
    
    @Value("${timeout: Default 5}")
    String paramTimeout;
    
    @HystrixCommand
    @GetMapping("/items")
    //public ResponseEntity<String> getCatalog()
    public String getCatalog()
    {
        System.out.println("---------------------------Catalog Service hit -----------------");
        //return restTemplate.getForObject("http://localhost:9085/pricing/items", String.class);
        return restTemplate.getForObject("http://pricing-svc/pricing/items", String.class);
    }
    
    
    
    
    @HystrixCommand
    @GetMapping("/param/timeout")
    public String getParam()
    {
        System.out.println("---------------------------Catalog Service getParam hit -----------------");        
        return paramTimeout;
    }
}
