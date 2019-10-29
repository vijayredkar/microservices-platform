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
@RequestMapping("/inventory")
public class CatalogController {

	Logger logger = LoggerFactory.getLogger(CatalogController.class);
	
    @Autowired
    RestTemplate restTemplate;
    
    @Value("${timeout: 8}")
    String paramTimeout;
    
    @Value("${source: unknown}")
    String configSrc;
    
    @HystrixCommand
    @GetMapping("/items")
    public String getCatalog()
    {
    	logger.info("Catalog Service print configSrc "+configSrc);
    	logger.info("Catalog Service print paramTimeout "+paramTimeout);
    	
        //String itemPrice =  return restTemplate.getForObject("http://localhost:9085/pricing/items", String.class);//without API-Gateway support
        String itemPrice = restTemplate.getForObject("http://pricing-svc/pricing/items", String.class);//with API-Gateway support
        
        //return "ITEM NAME : Coffee Mug : " + "PRICE : "+itemPrice;
        return formatResponse(itemPrice);
    }     
        
    private String formatResponse(String input)
    { 
    	return 
    			"<html>\r\n" + 
    			"<head>\r\n" + 
    			"<H1 align=\"center\">Online Catalog</H1>\r\n" +
    			"</head>\r\n" + 
    			"<body>\r\n" + 
    			"\r\n" + 
    			"<table border=\"1\" cellpadding=\"0\" cellspacing=\"0\" style=\"margin-left:auto;margin-right:auto;\">\r\n" +
    			"  <tr>\r\n" + 
    			"    <th>ITEM</th>\r\n" + 
    			"    <th>PRICE</th>\r\n" + 
    			"  </tr>\r\n" + 
    			"  <tr>\r\n" + 
    			"    <td>Coffee Mug</td>\r\n" + 
    			"    <td>" +  input  + "</td>\r\n" + 
    			"  </tr>\r\n" + 
    			"</table>\r\n" + 
    			"\r\n" + 
    			"</body>\r\n" + 
    			"</html>";    	
    }
}
