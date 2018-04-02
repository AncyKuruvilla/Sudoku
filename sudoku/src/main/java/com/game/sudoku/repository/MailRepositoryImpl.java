package com.game.sudoku.repository;

import com.game.sudoku.entity.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;

/**
 * Mail Repository Implementation.
 */
@Repository
public class MailRepositoryImpl implements MailRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private static Logger LOGGER = LoggerFactory.getLogger(MailRepositoryImpl.class);

    @Override
    public Mail addOrUpdate(Mail mail) {
        LOGGER.info("Inserting send mail data in DB");
        return entityManager.merge(mail);
    }

    @Override
    public Mail findByDate(LocalDate date) {
        LOGGER.info("Finding send mail detail from DB based on date "+ date);
        return entityManager.find(Mail.class,date);
    }
}
