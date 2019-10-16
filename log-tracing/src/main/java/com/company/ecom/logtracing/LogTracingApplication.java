package com.company.ecom.logtracing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class LogTracingApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogTracingApplication.class, args);
	}

}
