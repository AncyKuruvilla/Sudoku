package com.game.sudoku.service.email;

import com.game.sudoku.entity.User;
import com.game.sudoku.model.SudokuGrid;

import java.util.List;

/**
 * Mail Content Service.
 */
public interface MailContentService {
    /**
     * Create content of the mail to send the puzzle.
     * @param user
     * @param sudoku
     * @return mail content
     */
    String buildPuzzleMail(User user, SudokuGrid sudoku);

    /**
     * Create content of the mail to send the solution.
     * @param user
     * @param solution
     * @return mail content
     */
    String buildSolutioneMail(User user, List<List<Integer>> solution);
}
