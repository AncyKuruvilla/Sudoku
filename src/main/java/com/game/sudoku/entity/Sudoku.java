package com.game.sudoku.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by ancy on 09-03-2018.
 */
@Entity
public class Sudoku {
    @Id
    @GeneratedValue
    private int Id;

    @Column( length = 1000 )
    private String solution;

    public Sudoku(){

    }

    public Sudoku(String solution){
        this.solution = solution;
    }

    public int getId() {
        return Id;
    }
}
