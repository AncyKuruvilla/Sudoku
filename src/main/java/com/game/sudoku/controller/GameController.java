package com.game.sudoku.controller;

import com.game.sudoku.model.SudokuGrid;
import com.game.sudoku.service.GameService;
import com.game.sudoku.service.ValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by jino-ancy on 09-03-2018.
 */
@RestController
public class GameController {
    @Autowired
    private GameService gameService;

    @Autowired
    private ValidatorService validatorService;



    /**
     * End point to generate a new SudokuGrid grid.
     * Generated sudoku grid is saved in database.
     */
    @GetMapping(value = "/newgame", produces = {"application/json"})
    public List<List<Integer>> generateNewGame(){
        SudokuGrid sudoku = gameService.createNsaveNewGame();
        return sudoku.getSolution();
    }

    @PostMapping(value = "/validate/{id}", params = "param", consumes = {"application/xml","application/json"},
            produces = "application/xml")
    public boolean validate(@RequestBody List<List<Integer>> grid, @PathVariable int id, @RequestParam(required = false) String param){
        if (id == 10) {
            throw new IllegalArgumentException("Grid should not be null!");
        }
        return validatorService.validateGrid(grid);
    }
}
