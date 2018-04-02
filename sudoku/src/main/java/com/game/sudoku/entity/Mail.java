package com.game.sudoku.entity;

import javax.persistence.*;
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

    public Mail(LocalDate date, Sudoku sudoku) {
        this.date = date;
        this.sudoku = sudoku;
    }

    public Sudoku getSudoku() {
        return sudoku;
    }
}
