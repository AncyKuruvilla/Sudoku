package com.game.sudoku.service.email;

import com.game.sudoku.entity.User;
import com.game.sudoku.model.SudokuGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;


/**
 * Mail Content Service Implementation.
 */
@Service
public class MailContentServiceImpl implements MailContentService{

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public String buildPuzzleMail(User user, SudokuGrid sudoku) {
        Context context = new Context();
        context.setVariable("user", user);
        context.setVariable("sudoku",sudoku);
        return templateEngine.process("puzzle_mail_template",context);
    }

    @Override
    public String buildSolutioneMail(User user, List<List<Integer>> solution) {
        Context context = new Context();
        context.setVariable("user", user);
        context.setVariable("solution", solution);
        return templateEngine.process("solution_mail_template",context);
    }

    @Override
    public String buildWelcomeMale(User user) {
        Context context = new Context();
        context.setVariable("user",user);
        return templateEngine.process("welcome_template",context);
    }
}
