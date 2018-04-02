package com.game.sudoku.service.email;

import com.game.sudoku.entity.Mail;

import java.time.LocalDate;

/**
 * Mail Service.
 */
public interface MailService {
    /**
     * Send mail to the user with sudoku puzzle.
     * @param recipient
     * @param subject
     * @param text
     */
    void send(String recipient, String subject, String text);

    /**
     * Insert mail detail in Database.
     * @param mail
     * @return @{@link Mail} object
     */
    Mail insert(Mail mail);

    /**
     * Find sudoku puzzle from Database based on date
     * @param date
     * @return
     */
    Mail findByDate(LocalDate date);
}
