package com.game.sudoku.service;

import com.game.sudoku.entity.Sudoku;
import com.game.sudoku.model.SudokuGrid;

/**
 * Created by jino-ancy on 20-03-2018.
 */
public interface GameService {
    Sudoku find(int Id);
    SudokuGrid createNsaveNewGame();
}
