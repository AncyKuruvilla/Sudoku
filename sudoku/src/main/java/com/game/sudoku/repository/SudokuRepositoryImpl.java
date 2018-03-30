package com.game.sudoku.repository;

import com.game.sudoku.entity.Sudoku;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Sudoku Repository Implementation
 */
@Repository
public class SudokuRepositoryImpl implements SudokuRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private static Logger LOGGER = LoggerFactory.getLogger(SudokuRepositoryImpl.class);

    @Override
    public Sudoku findById(int Id) {
        return entityManager.find(Sudoku.class,Id);
    }

    @Override
    @Transactional
    public Sudoku addOrUpdate(Sudoku sudoku) {
        return entityManager.merge(sudoku);
    }
}
