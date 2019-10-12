package com.org.business.pricing;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pricing")
public class PricingController {
    
    @GetMapping("/items")
    @HystrixCommand(fallbackMethod="getDefaultPricing")
    public String getPricing()
    {
        System.out.println("---------------- pricing controller ");
        /*String a= null;
        a.length();*/
        
        return "price of item";        
    }
    
    public String getDefaultPricing()
    {
        System.out.println("---------------- pricing defaulted ");
        return "pricing defaulted";
    }

}
