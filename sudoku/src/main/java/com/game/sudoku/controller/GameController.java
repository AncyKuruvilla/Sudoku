package com.game.sudoku.controller;

import com.game.sudoku.model.SudokuGrid;
import com.game.sudoku.service.GameService;
import com.game.sudoku.service.ValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Game Controller
 */
@RestController
public class GameController {
    @Autowired
    private GameService gameService;

    @Autowired
    private ValidatorService validatorService;

    /**
     * Rest End point to generateSolution a new SudokuGrid grid.
     * Generated grid is saved in database.
     * @return
     */
    @GetMapping(value = "/game", produces = {"application/json"})
    public List<List<Integer>> generateNewGame(){
        SudokuGrid sudoku = gameService.create();
        gameService.save(sudoku);
        return sudoku.getPuzzle();
    }

    /**
     * Validate the Sodoku solution.
     * @param grid
     * @return
     */
    @PostMapping(value ="/validate", consumes = {"application/xml","application/json"},
            produces = "application/xml")
    public boolean validate(@RequestBody List<List<Integer>> grid){
        return validatorService.validateGrid(grid);
    }
}
