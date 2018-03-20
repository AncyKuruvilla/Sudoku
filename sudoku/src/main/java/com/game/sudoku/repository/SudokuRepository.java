package com.game.sudoku.repository;

import com.game.sudoku.entity.Sudoku;

/**
 * Created by jino-ancy on 20-03-2018.
 */
public interface SudokuRepository {
    Sudoku findById(int Id);
    Sudoku insert(Sudoku sudoku);
}
