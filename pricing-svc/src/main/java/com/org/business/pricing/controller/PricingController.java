package com.org.business.pricing.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/pricing")
public class PricingController {
  
	Logger logger = LoggerFactory.getLogger(PricingController.class);
	
	@Value("${timeout: 8}")
    String paramTimeout;
	
	@Value("${source: unknown}")
    String configSrc;
	
	
    @GetMapping("/items")
    @HystrixCommand(fallbackMethod="getDefaultPricing")
    public String getPricing()
    {
    	logger.info("pricing Service configSrc "+configSrc);
    	logger.info("pricing Service paramTimeout "+paramTimeout);        
        
        return "$ 25.00";        
    }
    
    public String getDefaultPricing()
    {
        logger.info("pricing fault tolerance invoked ");
        return "$ 10.00";
    }

}
