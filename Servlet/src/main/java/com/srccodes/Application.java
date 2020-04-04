package com.srccodes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;


@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.srccodes.repository")
public class Application extends SpringBootServletInitializer{
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	 public @Bean MongoClient mongoClient() {
	       return new MongoClient("mongo",27017);
	   }
	 public @Bean MongoTemplate mongoTemplate() {
	      return new MongoTemplate(mongoClient(), "myMongodb");
	  }
}
