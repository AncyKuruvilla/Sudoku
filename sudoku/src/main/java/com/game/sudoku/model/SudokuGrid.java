package com.game.sudoku.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Sudoku Grid
 */
public class SudokuGrid {
    private int solutionId;
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

    public int getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(int solutionId) {
        this.solutionId = solutionId;
    }
}