package com.org.business.catalog.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import com.netflix.loadbalancer.RoundRobinRule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class RibbonConfig {
    
    @Autowired
    IClientConfig clientConfig;
    
    @Bean
    public IPing iRule()
    {
        return new PingUrl(); //ping to check if server is alive
    }
    
    @Bean
    public IRule iConfig()
    {
        return new RoundRobinRule();//load balancing strategy
    }

}
