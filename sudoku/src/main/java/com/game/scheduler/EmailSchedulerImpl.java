package com.game.scheduler;

import com.game.email.MailService;
import com.game.sudoku.model.SudokuGrid;
import com.game.sudoku.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by jino-ancy on 20-03-2018.
 */
@Component
public class EmailSchedulerImpl implements EmailScheduler {

    private static Logger LOGGER = LoggerFactory.getLogger(EmailSchedulerImpl.class);

    @Autowired
    private GameService gameService;

    @Autowired
    private MailService mailService;

    @Scheduled(cron = "0 * * * * ?")
    @Override
    public void sendPuzzel() {
        LOGGER.info("called scheduled task to send the puzzle");
        SudokuGrid sudokuGrid = gameService.createNsaveNewGame();
        mailService.send("ancy.kuruvilla@hotmail.com",
                "Sudoku-Puzzle",
                sudokuGrid.getSolution().toString());
    }

    @Override
    public void sendSolution() {

    }
}
