package com.game.sudoku.service;

import com.game.sudoku.entity.User;
import com.game.sudoku.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User Service Implementation.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public User addOrUpdate(User user) {
        return userRepository.addOrUpdate(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }
}
