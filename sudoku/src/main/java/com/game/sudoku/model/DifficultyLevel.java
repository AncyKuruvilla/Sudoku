package com.game.sudoku.model;

/**
 * Difficulty Level
 */
public enum DifficultyLevel {
    EASY (24),
    MEDIUM (36),
    HARD (50);

    private final int columnSkipCount;

    private DifficultyLevel(int columnSkipCount) {
        this.columnSkipCount = columnSkipCount;
    }

    public int getColumnSkipCount() {
        return columnSkipCount;
    }
}
