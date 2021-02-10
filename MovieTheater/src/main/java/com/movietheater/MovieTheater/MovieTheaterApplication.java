package com.movietheater.MovieTheater;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import static com.movietheater.MovieTheater.components.Database.buildDatabase;

@SpringBootApplication
@EnableJpaAuditing
public class MovieTheaterApplication {

	public static void main(String[] args) throws Exception {
		buildDatabase();
		SpringApplication.run(MovieTheaterApplication.class, args);
	}

}
