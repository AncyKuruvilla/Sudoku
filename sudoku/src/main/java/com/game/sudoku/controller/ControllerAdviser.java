package com.game.sudoku.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller adviser
 */
@ControllerAdvice
public class ControllerAdviser {

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public String handleException() {
        return "some error ocuured!!";
    }
}
