package com.javasampleapproach.fcm.pushnotif;

import static com.google.common.collect.Lists.newArrayList;

import java.util.Collections;

import javax.servlet.ServletContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

		@Bean
	    public Docket api(ServletContext servletContext) { 
	        return new Docket(DocumentationType.SWAGGER_2)  
	          .select()                                  
	          .apis(RequestHandlerSelectors.any())            
	          .paths(PathSelectors.any())                         
	          .build().apiInfo(apiInfo()).pathProvider(new RelativePathProvider(servletContext));                                           
	    }

	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo("My REST API", "Some custom description of API.", "API TOS", "Terms of service",
				"Robert Rosario"+"www.example.com", "robertrosarioselvaraj@gmail.com", "License of API");
		
		return apiInfo;
	}
}
