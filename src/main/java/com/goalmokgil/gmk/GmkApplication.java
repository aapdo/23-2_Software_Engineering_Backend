package com.goalmokgil.gmk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // ?
public class GmkApplication {

	public static void main(String[] args) {
		SpringApplication.run(GmkApplication.class, args);
	}

}
