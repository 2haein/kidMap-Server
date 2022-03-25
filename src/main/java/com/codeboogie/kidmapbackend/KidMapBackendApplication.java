package com.codeboogie.comfortbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EntityScan("com.codeboogie.kidmapbackend")
@EnableMongoRepositories("com.codeboogie.kidmapbackend")
@SpringBootApplication
public class KidMapBackendApplication {
    public static void main (String[] args) {
        SpringApplication.run(KidMapBackendApplication.class, args);
    }
}