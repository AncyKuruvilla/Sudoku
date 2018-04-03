package com.game.sudoku.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDate;

/**
 * Mail
 */
@Entity
public class Mail {
    @Id
    @GeneratedValue
    private int Id;
    private LocalDate date;
    @OneToOne
    private Sudoku sudoku;

    private Mail() {
    }

    public Mail(LocalDate date, Sudoku sudoku) {
        this.date = date;
        this.sudoku = sudoku;
    }

    public Sudoku getSudoku() {
        return sudoku;
    }
}
