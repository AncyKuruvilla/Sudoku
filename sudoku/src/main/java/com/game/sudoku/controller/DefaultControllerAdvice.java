package com.game.sudoku.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller advice.
 */
@ControllerAdvice
public class DefaultControllerAdvice {

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public String handleException() {
        return "some error ocuured!!";
    }
}
