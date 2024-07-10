package com.samanecorporation.sercurityservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class SercurityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SercurityServiceApplication.class, args);
	}

}
