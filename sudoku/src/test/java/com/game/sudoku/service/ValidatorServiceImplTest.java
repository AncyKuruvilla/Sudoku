package com.game.sudoku.service;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class ValidatorServiceImplTest {
    @Test
    public void validateBlock() throws Exception {
        List<List<Integer>> grid =  new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            grid.add(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        }

        Assert.assertThat(new ValidatorServiceImpl().validateGrid(grid), Matchers.is(false));
    }

}