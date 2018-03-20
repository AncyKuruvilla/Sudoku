package com.game.sudoku.repository;

import com.game.sudoku.entity.Sudoku;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * Created by jino-ancy on 20-03-2018.
 */
@Repository
@Transactional
public class SudokuRepositoryImpl implements SudokuRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Sudoku findById(int Id) {
        return entityManager.find(Sudoku.class,Id);
    }

    @Override
    public Sudoku insert(Sudoku sudoku) {
        return entityManager.merge(sudoku);
    }
}
