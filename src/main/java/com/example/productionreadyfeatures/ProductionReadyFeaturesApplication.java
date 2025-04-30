package com.example.productionreadyfeatures;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class ProductionReadyFeaturesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductionReadyFeaturesApplication.class, args);
    }

}
