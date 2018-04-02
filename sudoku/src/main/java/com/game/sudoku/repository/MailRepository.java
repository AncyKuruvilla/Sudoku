package com.game.sudoku.repository;

import com.game.sudoku.entity.Mail;

import java.time.LocalDate;

/**
 * Mail Repository
 */
public interface MailRepository {
    /**
     * To addOrUpdate send mail detail in DB
     * @param mail
     * @return @{@link Mail} object
     */
    Mail addOrUpdate(Mail mail);

    /**
     * To find send mail detail by date
     * @return @{@link Mail} object
     */
    Mail findByDate(LocalDate date);
}
