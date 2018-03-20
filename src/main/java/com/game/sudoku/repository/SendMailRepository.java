package com.game.sudoku.repository;

import com.game.sudoku.entity.SendMail;

/**
 * Created by jino-ancy on 20-03-2018.
 */
public interface SendMailRepository {
    SendMail insert(SendMail sendMail);
    SendMail findById(int Id);
}
