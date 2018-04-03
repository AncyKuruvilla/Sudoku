package com.game.sudoku.scheduler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.sudoku.entity.Mail;
import com.game.sudoku.entity.Sudoku;
import com.game.sudoku.entity.User;
import com.game.sudoku.model.SudokuGrid;
import com.game.sudoku.service.GameService;
import com.game.sudoku.service.UserService;
import com.game.sudoku.service.email.MailContentService;
import com.game.sudoku.service.email.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private UserService userService;

    @Autowired
    private MailContentService mailContentService;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Send an email with Sudoku puzzle everyday.
     */
    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    @Override
    public void sendPuzzel(){
        LOGGER.info("Called scheduled task to send the puzzle.");
        //create puzzle
        SudokuGrid sudokuGrid = gameService.create();
        //save solution
        Sudoku sudoku  = gameService.save(sudokuGrid);
        //get all users
        List<User> users =  userService.getAll();
        //send mail
        for(User user: users) {
            //build content
            String content = mailContentService.buildPuzzleMail(user,sudokuGrid);
            mailService.send(user.getEmail(),"Sudoku-Puzzle", content);
        }
        //store sent mail detail
        mailService.insert(new Mail(LocalDate.now(),gameService.find(sudoku.getId())));
    }

    /**
     * Send sudoku solution everyday.
     */
    @Scheduled(cron = "20 0/1 * 1/1 * ?")
    @Override
    public void sendSolution() {
        LOGGER.info("Called scheduled task to send the puzzle.");
        LocalDate date = LocalDate.now();
        //Get solution based on date
        Mail sentMail = mailService.findByDate(date);
        if (sentMail == null) {
            return;
        }
        List<List<Integer>> solution = new ArrayList<List<Integer>>();
        try {
            solution.addAll(objectMapper
                    .readValue(sentMail.getSudoku().getSolution(), new TypeReference<List<List<Integer>>>() {}));
        } catch (IOException e) {
            LOGGER.error("Could not read the solution!");
        }
        //get all users
        List<User> users =  userService.getAll();
        //send mail
        for(User user: users) {

            //build content
            String content = mailContentService.buildSolutioneMail(user, solution);
            mailService.send(user.getEmail(),"Sudoku-Solution", content);
        }
    }
}
