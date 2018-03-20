package com.game.sudoku.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jino-ancy on 20-03-2018.
 */
public class SudokuGrid {
    private int solutionID;
    private List<List<Integer>> solution;
    private List<List<Integer>> puzzle;

    public SudokuGrid() {
        this.solution = new ArrayList<List<Integer>>();
        this.puzzle = new ArrayList<List<Integer>>();
    }

    public List<List<Integer>> getSolution() {
        return solution;
    }

    public void setSolution(List<List<Integer>> grid) {
        this.solution = grid;
    }

    public List<List<Integer>> getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(List<List<Integer>> puzzle) {
        this.puzzle = puzzle;
    }

    public int getSolutionID() {
        return solutionID;
    }

    public void setSolutionID(int solutionID) {
        this.solutionID = solutionID;
    }
}
