package com.game.sudoku.service;

import com.game.sudoku.entity.User;
import com.game.sudoku.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User Service Implementation.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addOrUpdate(User user) {
        return userRepository.addOrUpdate(user);
    }
}
