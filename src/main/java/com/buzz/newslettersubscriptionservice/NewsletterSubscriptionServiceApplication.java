package com.buzz.newslettersubscriptionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.buzz"})
@EnableAutoConfiguration
@EnableJpaRepositories
public class NewsletterSubscriptionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsletterSubscriptionServiceApplication.class, args);
	}

}
