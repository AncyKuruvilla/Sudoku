package com.game.sudoku.entity;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by jino-ancy on 20-03-2018.
 */
@Entity
public class SendMail {
    @Id
    @GeneratedValue
    private int Id;
    private LocalDate date;
    @OneToOne
    private Sudoku sudoku;
}
