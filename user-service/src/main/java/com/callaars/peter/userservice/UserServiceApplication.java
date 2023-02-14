package com.callaars.peter.userservice;

import com.callaars.peter.userservice.model.User;
import com.callaars.peter.userservice.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(UserRepository userRepository) {
		return args -> {
//			User user1 = User.builder()
//					.name("Peter")
//					.email("Peter.Callaars@gmail.com")
//					.birthDate(LocalDate.of(1990, Month.DECEMBER, 2))
//					.build();
//
//			User user2 = User.builder()
//					.name("Henk")
//					.email("Henk@Yahoo.com")
//					.birthDate(LocalDate.of(1960, Month.APRIL, 6))
//					.build();
//
//			userRepository.save(user1);
//			userRepository.save(user2);
		};
	}

}
