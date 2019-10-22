package com.org.business.catalog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	Logger logger = LoggerFactory.getLogger(CatalogController.class);
	
    @Autowired
    RestTemplate restTemplate;
    
    //@Value("${timeout: Default 5}")
    @Value("${timeout: 8}")
    String paramTimeout;
    
    @HystrixCommand
    @GetMapping("/items")
    //public ResponseEntity<String> getCatalog()
    public String getCatalog()
    {
        System.out.println("---------------------------Catalog Service hit -----------------paramTimeout "+paramTimeout);
        logger.info("---------------------------Catalog Service hit log-----------------paramTimeout "+paramTimeout);
        //return restTemplate.getForObject("http://localhost:9085/pricing/items", String.class);
        return restTemplate.getForObject("http://pricing-svc/pricing/items", String.class);
    }
    
    
    
    
    @HystrixCommand
    @GetMapping("/param/timeout")
    public String getParam()
    {
        System.out.println("---------------------------Catalog Service getParam hit -----------------");
        logger.info("---------------------------Catalog Service getParam hit log-----------------");
        return paramTimeout;
    }
}
