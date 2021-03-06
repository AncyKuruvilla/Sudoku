package com.game.sudoku.controller;

import com.game.sudoku.entity.User;
import com.game.sudoku.service.UserService;
import com.game.sudoku.service.email.MailContentService;
import com.game.sudoku.service.email.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * User Controller.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailContentService mailContentServiceService;

    private MailService mailService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value ="/user",consumes = {"application/xml","application/json"},
            produces = "application/xml")
    public Integer addUser(@RequestBody User user){
        user = userService.addOrUpdate(user);
        String content = mailContentServiceService.buildWelcomeMale(user);
        mailService.send(user.getEmail(),"Welcome",content);
        return user.getId();
    }
}
