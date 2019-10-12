package com.org.business.catalog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	
	@Bean
	public Docket api()
	{
		return new Docket(DocumentationType.SWAGGER_2)
						.select()
						.apis(RequestHandlerSelectors
								.basePackage("com.org.business.catalog.controller"))
						.paths(PathSelectors.regex("/.*"))
						.build()
						.apiInfo(apiPointsInfo())
				;
	}
	
	 public ApiInfo apiPointsInfo()
	 {
		 
		return new ApiInfoBuilder().title("Catalog Service Swagger2 Documentation")					
									.build();
	 }
	 
	 
	
	
	

}
