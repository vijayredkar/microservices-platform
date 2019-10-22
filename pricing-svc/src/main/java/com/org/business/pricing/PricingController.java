package com.org.business.pricing;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/pricing")
public class PricingController {
  
	Logger logger = LoggerFactory.getLogger(PricingController.class);
	
    @GetMapping("/items")
    @HystrixCommand(fallbackMethod="getDefaultPricing")
    public String getPricing()
    {
        System.out.println("---------------- pricing controller ");
        logger.info("---------------- pricing controller log");
        /*String a= null;
        a.length();*/
        
        return "price of item";        
    }
    
    public String getDefaultPricing()
    {
        System.out.println("---------------- pricing defaulted ");
        logger.info("---------------- pricing defaulted log");
        return "pricing defaulted";
    }

}
