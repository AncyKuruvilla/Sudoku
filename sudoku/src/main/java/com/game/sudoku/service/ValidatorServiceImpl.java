package com.game.sudoku.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jino-ancy on 11-03-2018.
 */
@Service
public class ValidatorServiceImpl implements ValidatorService{
    private static Logger LOGGER = LoggerFactory.getLogger(ValidatorServiceImpl.class);

    /**
     * Validating Grid
     * @param grid
     * @return
     */
    @Override
    public boolean validateGrid(List<List<Integer>> grid) {
        LOGGER.info("Validating the grid");
        List<Integer> column = new ArrayList<>();
        for(int i= 0;i<9;i++){
            if(!validate(grid.get(i))){
                return false;
            }
            column.clear();
            for(int j=0;j<9;j++){
                column.add(grid.get(j).get(i));
            }
            if(!validate(column)){
                return false;
            }
        }
        return true;
    }

    /**
     * Validating Row
     * check if the row contain value from 1-9
     * check if row contain unique value
     * @param row
     * @return
     */
    private boolean validate(List<Integer> row){
        LOGGER.info("Validating row");
        if(row.size()!= 9){
            return false;
        }
        int sum = row.stream().filter(value->value <10 && value > 0)
                .distinct().limit(9)
                .reduce(0, Integer::sum);

        return sum == 45;
    }

    //TODO:validate block
    private boolean validateBlock(){
        for(int i=0;i<9;i++){

        }
        return true;
    }
}
