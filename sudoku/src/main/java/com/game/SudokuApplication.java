package com.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan({"com.game.sudoku","com.game.sudoku.scheduler","com.game.sudoku.service.email"})
@SpringBootApplication
@EnableScheduling
public class SudokuApplication {
	private static Logger LOGGER = LoggerFactory.getLogger(SudokuApplication.class);

	public static void main(String[] args) {
		LOGGER.info("Starting application.");
		SpringApplication.run(SudokuApplication.class, args);
	}
}
