package com.org.business.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.org.business.catalog.config.RibbonConfig;

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@EnableHystrixDashboard
@RibbonClient(name="catalog", configuration=RibbonConfig.class)
@RefreshScope
public class CatalogSvcApplication {
    

	public static void main(String[] args) {
		SpringApplication.run(CatalogSvcApplication.class, args);
	}
	
	@Bean
	@LoadBalanced
	RestTemplate restTemplate()
	{
	   return new RestTemplate();	    
	}

}
