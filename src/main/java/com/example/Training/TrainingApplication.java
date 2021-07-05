package com.example.Training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableAutoConfiguration
@Configuration

public class TrainingApplication implements WebMvcConfigurer{

	
	public static void main(String[] args) {
		SpringApplication.run(TrainingApplication.class, args);
	}
	String myExternalFilePath = "file:C:/Users/Saad/Downloads/Training/src/main/resources/uploads/";
	@Override
	        public void addResourceHandlers(ResourceHandlerRegistry registry) {
	            registry.addResourceHandler("/src/main/resources/uploads/**")
	            .addResourceLocations(myExternalFilePath)
	            .setCachePeriod(0);
	        }
	
	    
}
