package com.game.sudoku.service;

import com.game.sudoku.entity.User;

/**
 * User Service
 */
public interface UserService {
    /**
     * Add user.
     * @param user
     * @return @{@link User} object
     */
    User addOrUpdate(User user);
}
