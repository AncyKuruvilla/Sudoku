package com.game.sudoku.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Validator Service Implementation.
 */
@Service
public class ValidatorServiceImpl implements ValidatorService {
    private static Logger LOGGER = LoggerFactory.
            getLogger(ValidatorServiceImpl.class);

    @Override
    public boolean validateGrid(List<List<Integer>> grid) {
        LOGGER.info("Validating the grid");
        List<Integer> column = new ArrayList<>();
        for(int i = 0; i < grid.size(); i++){
            if(!validate(grid.get(i))){
                return false;
            }
            column.clear();
            for(int j = 0; j < grid.size(); j++){
                column.add(grid.get(j).get(i));
            }
            if(!validate(column)){
                return false;
            }
        }
        return true;
    }

    /**
     * Validating Row.
     * Check if the row contain value from 1-9 and
     * also check if row contain unique values.
     * @param row
     * @return @boolean
     */
    private boolean validate(List<Integer> row){
        LOGGER.info("Validating row and column");
        if(row.size()!= row.size()){
            return false;
        }

        int sum = row.stream().filter(value -> value < 10 && value > 0)
                .distinct().limit(row.size())
                .reduce(0, Integer::sum);
        System.out.print(" ");
        return sum == 45;
    }

    /**
     * Validating all the blocks of the sudoku grid.
     * @param grid
     * @return @boolean
     */
    private boolean validateBlock(List<List<Integer>> grid){
        LOGGER.info("Validating block");
        boolean checkValidity = true;
        int startRowIndex = 0;
        int startColumnIndex = 0;
        for(int i = startRowIndex; i < grid.size(); i += 3){
            for(int j = startColumnIndex; j < grid.size(); j += 3) {
                checkValidity = validateBlock(grid, i, j);
                if(!checkValidity) {
                    return checkValidity;
                }
            }
        }
        return checkValidity;
    }

    /**
     * Validating individual block.
     * @param grid
     * @param startRowIndex
     * @param startColumnIndex
     * @return @boolean
     */
     private boolean validateBlock(List<List<Integer>> grid,
                                   int startRowIndex, int startColumnIndex){
        List<Integer> block = new ArrayList<>();
        boolean checkValidity = true;
        int endRowIndex = startRowIndex + 3;
        int endColumnIndex = startColumnIndex + 3;
        for(int i = startRowIndex; i < endRowIndex; i++){
            for(int j = startColumnIndex; j < endColumnIndex; j++){
                block.add(grid.get(i).get(j));
            }
        }
        return validate(block);
    }
}
