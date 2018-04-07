package com.game.sudoku.service;

import com.game.sudoku.entity.User;
import com.game.sudoku.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;

/**
 * @author : Ancy Kuruvilla
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void addOrUpdate(){
        User user = mock(User.class);
        //when
        userService.addOrUpdate(user);
        //then
        Mockito.verify(userRepository).addOrUpdate(user);
    }

    @Test
    public void getAll(){
        //when
        userService.getAll();
        //then
        Mockito.verify(userRepository).getAll();
    }

}