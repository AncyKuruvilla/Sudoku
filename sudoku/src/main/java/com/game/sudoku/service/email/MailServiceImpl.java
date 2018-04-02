package com.game.sudoku.service.email;

import com.game.sudoku.entity.Mail;
import com.game.sudoku.repository.MailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Mail Service Implementation.
 */
@Component
public class MailServiceImpl implements MailService {

    private static Logger LOGGER = LoggerFactory.getLogger(MailServiceImpl.class);

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MailRepository mailRepository;

    @Override
    public void send(String to, String subject, String text) {
        LOGGER.info("Sending email");
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            mailSender.send(message);
    }

    @Override
    public Mail insert(Mail mail) {
        return mailRepository.addOrUpdate(mail);
    }

    @Override
    public Mail findByDate(LocalDate date) {
        return mailRepository.findByDate(date);
    }
}
