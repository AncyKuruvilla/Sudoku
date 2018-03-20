package com.game.sudoku.service;

import java.util.List;

/**
 * Created by jino-ancy on 11-03-2018.
 */
public interface ValidatorService {
    boolean validateGrid(List<List<Integer>> grid);
}
