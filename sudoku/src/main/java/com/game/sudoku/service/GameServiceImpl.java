package com.game.sudoku.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.sudoku.entity.Sudoku;
import com.game.sudoku.model.DifficultyLevel;
import com.game.sudoku.model.SudokuGrid;
import com.game.sudoku.repository.SudokuRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Game service provide functionality to Sudoku puzzle and solution.
 */
@Service
public class GameServiceImpl implements GameService {

    private static Logger LOGGER = LoggerFactory.getLogger(GameServiceImpl.class);

    @Autowired
    private SudokuRepository sudokuRepository;

    @Autowired
    private ValidatorService validator;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Sudoku find(final int id) {
        return sudokuRepository.findById(id);
    }

    @Override
    public SudokuGrid create() {
        LOGGER.info("Generating sudoku grid.");
        SudokuGrid sudokuGrid = generateSolution();
        sudokuGrid = caretePuzzle(sudokuGrid, DifficultyLevel.MEDIUM);
        return sudokuGrid;
    }

    @Override
    @Transactional
    public Sudoku save(SudokuGrid sudokuGrid) {
        String solution = toJson(sudokuGrid.getSolution());
        if (solution == null) {
            throw new IllegalArgumentException("Could not store the solution: "
                    + sudokuGrid.getSolution() + " into database!");
        }
        Sudoku sudoku = new Sudoku(solution);
        LOGGER.info("Inserting solution in database");
        return sudokuRepository.addOrUpdate(sudoku);
    }

    /**
     * Convert given object into Json.
     * Package private for testing.
     */
    String toJson(Object value) {
        if (value == null) {
            return null;
        }
        String solution = null;
        try {
            solution = objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            LOGGER.error("Could not convert the object: {} into Json.", value);
        }
        return solution;
    }

    /**
     * Creating puzzle by removing random value from the solution.
     *
     * @param grid
     * @param level
     * @return @{@link SudokuGrid} object
     */
    SudokuGrid caretePuzzle(SudokuGrid grid, DifficultyLevel level) {
        LOGGER.info("Creating puzzle with skipped columns");
        List<List<Integer>> puzzle =
                new ArrayList<List<Integer>>(grid.getSolution().size());

        int sudokuSize = grid.getSolution().size() * grid.getSolution().get(0).size();
        List<Integer> skipList = IntStream.rangeClosed(1, sudokuSize / 2).map(i -> i < level.getColumnSkipCount() / 2 ? 0 : 1).boxed().collect(Collectors.toList());
        Collections.shuffle(skipList);

        List<Integer> reversedSkipList = new ArrayList<>(skipList);
        Collections.reverse(reversedSkipList);

        // adjust size of skip list if the sudoku size is odd
        if (sudokuSize % 2 != 0) {
            skipList.add(1);
        }
        skipList.addAll(reversedSkipList);
        int skipCount = 0;

        for (List<Integer> row : grid.getSolution()) {
            List<Integer> pRow = new ArrayList<>(row.size());
            for (int rowIdx = 0; rowIdx < row.size(); rowIdx++) {
                pRow.add(skipList.get(skipCount++) == 0 ? null : row.get(rowIdx));
            }
            puzzle.add(pRow);
        }

        grid.setPuzzle(puzzle);
        return grid;
    }

    /**
     * Generate a solved grid
     *
     * @return @{@link SudokuGrid} object
     */
    SudokuGrid generateSolution() {
        LOGGER.info("Creating solution");
        SudokuGrid sudoku = new SudokuGrid();
        List<Integer> row = generateBaseRow();
        fillGrid(sudoku, row);
        return sudoku;
    }

    /**
     * sudoku grid is populated with appropriate value
     *
     * @param sudoku
     * @param row
     */
    private void fillGrid(SudokuGrid sudoku, List<Integer> row) {
        LOGGER.info("Filling sudoku solution grid");
        List<List<Integer>> grid = sudoku.getSolution();
        for (int counter = 0; counter < 9; counter++) {
            if (counter != 0 && counter % 3 == 0) {
                row = shuffleRow(row);
            }
            List<Integer> newRow = new ArrayList<>();
            newRow.addAll(row);
            grid.add(newRow);
            rotateRow(row);
        }
    }

    /**
     * Generate the base row
     * Based on the base row the whole Sudoku Grid will be generated
     *
     * @return @{@link List<Integer>}
     */
    private List<Integer> generateBaseRow() {
        LOGGER.info("Generating base row");
        List<Integer> row = IntStream.range(1, 10)
                .boxed()
                .collect(Collectors.toList());
        Collections.shuffle(row);
        return row;
    }

    /**
     * The element position should not match with the old position
     * In this method the row is rotated with distance of 3
     *
     * @param baseRow
     */
    private void rotateRow(List<Integer> baseRow) {
        LOGGER.info("Rotating the value of row to distance of 3");
        Collections.rotate(baseRow, 3);
    }


    /**
     * Shuffel each group of 3 element in such a way
     * that the position doesnt match the previous row
     *
     * @param baseRow
     * @return @{@link List<Integer>}
     */
    private List<Integer> shuffleRow(List<Integer> baseRow) {
        LOGGER.info("Shuffleing every 3 element of the row");
        List<Integer> shuffledRow = new ArrayList<>();
        shuffledRow.addAll(splitNshuffelRow(0, 3, baseRow));
        shuffledRow.addAll(splitNshuffelRow(3, 6, baseRow));
        shuffledRow.addAll(splitNshuffelRow(6, 9, baseRow));
        return shuffledRow;
    }

    /**
     * Spliting the list with 3 element
     * and rotating it with distance 1
     *
     * @param fromIndex
     * @param toIndex
     * @param baseRow
     * @return @{@link List<Integer>}
     */
    private List<Integer> splitNshuffelRow(int fromIndex, int toIndex, List<Integer> baseRow) {
        List<Integer> subList = baseRow.subList(fromIndex, toIndex);
        Collections.rotate(subList, 1);
        return subList;
    }
}
