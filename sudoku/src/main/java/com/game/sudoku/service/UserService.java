package com.game.sudoku.service;

import com.game.sudoku.entity.User;

import java.util.List;

/**
 * User Service
 */
public interface UserService {
    /**
     * Add user.
     * @param user
     * @return {@link User} object
     */
    User addOrUpdate(User user);

    /**
     * Get all users.
     * @return {@link List<User>}
     */
    List<User> getAll();
}
