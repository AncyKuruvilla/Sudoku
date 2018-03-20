package com.game.sudoku.service;

import com.game.sudoku.entity.Sudoku;
import com.game.sudoku.model.SudokuGrid;
import com.game.sudoku.repository.SudokuRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jino-ancy on 20-03-2018.
 */
@Service
public class GameServiceImpl implements GameService {

    private static Logger LOGGER = LoggerFactory.getLogger(GameServiceImpl.class);

    @Autowired
    private GridGeneratorService gridGeneratorService;

    @Autowired
    private SudokuRepository sudokuRepository;

    /**
     * based on Id get solution from DB
     * @param Id
     * @return
     */
    @Override
    public Sudoku find(int Id)
    {
        return sudokuRepository.findById(Id);
    }

    /**
     * Generate new sudoku puzzle
     * save the solution in the DB
     * send the puzzle to all the user
     * save the send mail detail in DB
     * @return
     */
    @Override
    public SudokuGrid createNsaveNewGame() {
        LOGGER.info("Generating sudoku grid and saving it in DB");
        //Generate new sudoku puzzle
        SudokuGrid sudokuGrid = gridGeneratorService.generate();
        //store solution in DB
        Sudoku sudoku = insertNewSolutionInDB(sudokuGrid);
        sudokuGrid.setSolutionID(sudoku.getId());
        //todo: remove value to create a puzzle
        return sudokuGrid;
    }

    /**
     * Store sudoku solution in DB
     * @param sudokuGrid
     * @return
     */
    private Sudoku insertNewSolutionInDB(SudokuGrid sudokuGrid) {
        LOGGER.info("Inserting value in database");
        String solution = sudokuGrid.getSolution().toString();
        Sudoku sudoku = new Sudoku(solution);
        return sudokuRepository.insert(sudoku);
    }
}
