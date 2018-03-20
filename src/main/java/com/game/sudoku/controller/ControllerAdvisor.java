package com.game.sudoku.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by jino-ancy on 13-03-2018.
 */
@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public String handleException() {
        return "some error ocuured!!";
    }
}
