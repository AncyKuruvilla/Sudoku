package com.game.sudoku.service;

import java.util.List;

/**
 * Validator Service
 */
public interface ValidatorService {
    /**
     * Checks if the sudoku is valid or not.
     * @param grid
     * @return @boolean
     */
    boolean validateGrid(List<List<Integer>> grid);
}
