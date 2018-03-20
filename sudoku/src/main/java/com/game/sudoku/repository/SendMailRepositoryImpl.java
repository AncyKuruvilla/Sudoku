package com.game.sudoku.repository;

import com.game.sudoku.entity.SendMail;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * Created by jino-ancy on 20-03-2018.
 */
@Repository
@Transactional
public class SendMailRepositoryImpl implements SendMailRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public SendMail insert(SendMail sendMail) {
        return entityManager.merge(sendMail);
    }

    @Override
    public SendMail findById(int Id) {
        return entityManager.find(SendMail.class,Id);
    }
}
