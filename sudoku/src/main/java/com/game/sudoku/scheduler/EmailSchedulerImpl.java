package com.game.sudoku.scheduler;

import com.game.sudoku.entity.Mail;
import com.game.sudoku.entity.Sudoku;
import com.game.sudoku.model.SudokuGrid;
import com.game.sudoku.service.GameService;
import com.game.sudoku.service.email.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Email Scheduler Implementation.
 */
@Component
public class EmailSchedulerImpl implements EmailScheduler {

    private static Logger LOGGER = LoggerFactory.getLogger(EmailSchedulerImpl.class);

    @Autowired
    private GameService gameService;

    @Autowired
    private MailService mailService;

    /**
     * Send an email with Sudoku puzzle everyday at 9.00 am.
     */
    @Scheduled(cron = "0 0 7 * * ?")
    @Override
    public void sendPuzzel() {
        LOGGER.info("Called scheduled task to send the puzzle.");
        //create puzzle
        SudokuGrid sudokuGrid = gameService.create();
        //save solution
        Sudoku sudoku  = gameService.save(sudokuGrid);
        //send mail
        mailService.send("ancy.kuruvilla@hotmail.com",
                "Sudoku-Puzzle",
                sudokuGrid.getPuzzle().toString());

        //store sent mail detail
        mailService.insert(new Mail(LocalDate.now(),gameService.find(sudoku.getId())));
    }

    /**
     * Send sudoku solution everyday at 12.30 pm.
     */
    @Scheduled(cron = "0 30 12 * * ?")
    @Override
    public void sendSolution() {
        LOGGER.info("Called scheduled task to send the puzzle.");
        LocalDate date = LocalDate.now();
        //Get solution based on date
        Mail sentMail = mailService.findByDate(date);

        //TODO get all users

        //send mail
        mailService.send("ancy.kuruvilla@hotmail.com",
                "Sudoku-Solution",
                sentMail.getSudoku().getSolution());
    }
}
