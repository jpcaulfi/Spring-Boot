package com.canopy.canopyspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

import static com.canopy.canopyspring.components.Database.buildSimDatabase;

@SpringBootApplication
public class CanopySpringApplication {

	public static void main(String[] args) throws SQLException {
		buildSimDatabase();
		SpringApplication.run(CanopySpringApplication.class, args);
	}

}
