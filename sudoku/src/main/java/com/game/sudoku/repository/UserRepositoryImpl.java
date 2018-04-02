package com.game.sudoku.repository;

import com.game.sudoku.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * User Repository Implementation
 */
@Repository
public class UserRepositoryImpl implements UserRepository{

    @PersistenceContext
    private EntityManager entityManager;

    private static Logger LOGGER = LoggerFactory.getLogger(UserRepositoryImpl.class);

    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
        return entityManager.
                createQuery("Select * from user").
                getResultList();
    }

    @Override
    public User addOrUpdate(User user) {
        return entityManager.merge(user);
    }
}
