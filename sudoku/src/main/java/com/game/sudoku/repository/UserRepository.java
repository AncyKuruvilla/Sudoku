package com.game.sudoku.repository;

import com.game.sudoku.entity.User;

import java.util.List;

/**
 * User repository
 */
public interface UserRepository {
    /**
     * To find all user from DB
     * @return all the user details @{@link List<User>}
     */
    List<User> getAll();

    /**
     * Add user detail.
     * @param user
     * @return @{@link User}
     */
    User addOrUpdate(User user);
}
