package com.cts.favorite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class FavouritteListApplication {

	public static void main(String[] args) {
		SpringApplication.run(FavouritteListApplication.class, args);
	}

}
