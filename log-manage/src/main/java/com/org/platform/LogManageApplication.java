package com.org.platform;

import org.springframework.boot.SpringApplication;

import zipkin.server.EnableZipkinServer;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableZipkinServer
public class LogManageApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogManageApplication.class, args);
	}

}
