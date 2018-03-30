package com.game.sudoku.service;

import com.game.sudoku.entity.Sudoku;
import com.game.sudoku.model.SudokuGrid;

/**
 * Game Service
 */
public interface GameService {
    /**
     * Based on Id get solution from DB
     * @param Id
     * @return solution @{@link Sudoku}
     */
    Sudoku find(int Id);

    /**
     * Generate new Sudoku puzzle and solution
     * @return @{@link SudokuGrid} object
     */
    SudokuGrid create();

    /**
     * Save Sudoku
     * @param sudokuGrid
     * @return @{@link Sudoku} object
     */
    Sudoku save(SudokuGrid sudokuGrid);
}
