package com.game.sudoku.service;

import com.game.sudoku.model.SudokuGrid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jino-ancy on 09-03-2018.
 */
@Service
public class GridGeneratorServiceImpl implements GridGeneratorService{

    private static Logger LOGGER = LoggerFactory.getLogger(GridGeneratorServiceImpl.class);

    @Autowired
    private RowGeneratorService rowGeneratorService;

    /**
     * generate a solved sudoku grid
     * @return
     */
    @Override
    public SudokuGrid generate(){
        LOGGER.info("Generating sudoku grid");
        SudokuGrid sudoku = new SudokuGrid();
        // base row is generated baed on which the whole gril will be created
        List<Integer> row = rowGeneratorService.generate();
        fill(sudoku,row);
        return sudoku;
    }

    /**
     * sudoku grid is populated with appropriate value
     * @param sudoku
     * @param row
     */
    private void fill(SudokuGrid sudoku, List<Integer> row){
        LOGGER.info("Filling grid");
        List<List<Integer>> grid = sudoku.getSolution();
        //the base row need to be shuffeled after populating 3 rows
        for(int counter=0;counter<9;counter++){
            if(counter!=0 && counter%3 == 0) {
                row = rowGeneratorService.shuffle(row);
            }
            List<Integer> newRow = new ArrayList<>();
            newRow.addAll(row);
            grid.add(newRow);
            //base row need to be roated for each new row
            rowGeneratorService.rotate(row);
        }
    }
}
