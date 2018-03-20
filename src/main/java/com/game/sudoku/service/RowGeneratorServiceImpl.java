package com.game.sudoku.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by jino-ancy on 09-03-2018.
 */
@Service
public class RowGeneratorServiceImpl implements RowGeneratorService{
    private static Logger LOGGER = LoggerFactory.getLogger(RowGeneratorServiceImpl.class);

    //Generate the base row, based on which the whole SudokuGrid will be generated
    @Override
    public List<Integer> generate(){
        LOGGER.info("Generating base row");
        List<Integer> row = IntStream.range(1, 10)  // creates a stream of ints
                        .boxed()        // converts them to Integers
                        .collect(Collectors.toList());
        Collections.shuffle(row);
        return row;
    }

    // the element position should not match with the old position
    @Override
    public void rotate(List<Integer> baseRow){
        LOGGER.info("Rotating the value of row to distance of 3");
        Collections.rotate(baseRow,3);
    }

    //todo :hardcode position value?
    //todo: other better way?
    // need to shuffel each group of 3 element in such a way that

    @Override
    public List<Integer> shuffle(List<Integer> baseRow){
        LOGGER.info("Shuffeling every 3 element of the row");
        List<Integer> shuffledRow = new ArrayList<>();
        shuffledRow.addAll(splitNshuffel(0,3,baseRow));
        shuffledRow.addAll(splitNshuffel(3,6,baseRow));
        shuffledRow.addAll(splitNshuffel(6,9,baseRow));
        return shuffledRow;
    }

    private List<Integer> splitNshuffel(int fromIndex, int toIndex, List<Integer> baseRow){
        List<Integer> subList = baseRow.subList(fromIndex,toIndex);
        Collections.rotate(subList,1);
        return subList;
    }
}
