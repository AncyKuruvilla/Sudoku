package com.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SudokuApplication {
	private static Logger LOGGER = LoggerFactory.getLogger(SudokuApplication.class);

	public static void main(String[] args) {
		LOGGER.info("Starting application.");
		SpringApplication.run(SudokuApplication.class, args);
	}
}
