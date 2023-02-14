package com.callaars.peter.drinkservice;

import com.callaars.peter.drinkservice.repository.DrinkRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class DrinkServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DrinkServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(DrinkRepository drinkRepository) {
        return  args -> {};
    }
}
