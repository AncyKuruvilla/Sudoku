package com.game.sudoku.service;

import java.util.List;

/**
 * Created by jino-ancy on 09-03-2018.
 */
public interface RowGeneratorService {
    List<Integer> generate();
    List<Integer> shuffle(List<Integer> row);
    void rotate(List<Integer> row);
}
