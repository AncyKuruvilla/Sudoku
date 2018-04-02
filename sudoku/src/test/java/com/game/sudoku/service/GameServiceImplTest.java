package com.game.sudoku.service;

import com.game.sudoku.model.DifficultyLevel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by jino-ancy on 30-03-2018.
 */
public class GameServiceImplTest {

    @Test
    public void testSkipLogic() {

        List<List<Integer>> grid =  new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            grid.add(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        }

        DifficultyLevel level = DifficultyLevel.MEDIUM;
        List<Integer> skipList = IntStream.rangeClosed(1, 40).map(i -> i < level.getColumnSkipCount()/2 ? 0 : 1).boxed().collect(Collectors.toList());
        Collections.shuffle(skipList);

        List<Integer> reversedSkipList = new ArrayList<>(skipList);
        Collections.reverse(reversedSkipList);
        skipList.add(1);
        skipList.addAll(reversedSkipList);
        int skipCount = 0;

        for (int colIdx = 0; colIdx < grid.size(); colIdx++) {
            List<Integer> row = grid.get(colIdx);
            for (int rowIdx = 0; rowIdx < row.size(); rowIdx++) {
                System.out.print(skipList.get(skipCount++) + " ");
            }
            System.out.println("");
        }
    }

}