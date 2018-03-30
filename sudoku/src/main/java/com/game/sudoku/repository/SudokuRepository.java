package com.game.sudoku.repository;

import com.game.sudoku.entity.Sudoku;

/**
 * Sudoku Repository
 */
public interface SudokuRepository {
    /**
     * to find the sudoku by Id
     * @param Id
     * @return @{@link Sudoku} object
     */
    Sudoku findById(int Id);

    /**
     * add a sudoku in the DB
     * @param sudoku
     * @return @{@link Sudoku} object
     */
    Sudoku addOrUpdate(Sudoku sudoku);
}
