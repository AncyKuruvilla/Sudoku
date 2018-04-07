package com.game.sudoku.service;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

/**
 *
 */
public class ValidatorServiceImplTest {
    private ValidatorService validatorService = new ValidatorServiceImpl();

    @Test
    public void validateGridInvalidValueTest(){
        //when
        List<List<Integer>> grid =  new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            grid.add(asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        }
        //then
        Assert.assertThat(validatorService.validateGrid(grid), Matchers.is(false));
    }

    @Test
    public void validateGridValidValueTest(){
        //when
        List<List<Integer>> grid =  asList(
                asList(2,6,8,1,5,9,3,4,7),
                asList(3,4,7,2,6,8,1,5,9),
                asList(1,5,9,3,4,7,2,6,8),
                asList(8,2,6,9,1,5,7,3,4),
                asList(7,3,4,8,2,6,9,1,5),
                asList(9,1,5,7,3,4,8,2,6),
                asList(6,8,2,5,9,1,4,7,3),
                asList(4,7,3,6,8,2,5,9,1),
                asList(5,9,1,4,7,3,6,8,2));
        //then
        Assert.assertThat(validatorService.validateGrid(grid),Matchers.is(true));
    }
}