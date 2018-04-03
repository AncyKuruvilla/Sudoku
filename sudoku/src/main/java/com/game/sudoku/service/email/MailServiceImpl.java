package com.game.sudoku.service.email;

import com.game.sudoku.entity.Mail;
import com.game.sudoku.repository.MailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    public void send(String recipient, String subject, String content) {
        LOGGER.info("Sending email");
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(recipient);
            messageHelper.setSubject(subject);
            messageHelper.setText(content, true);
        };
        mailSender.send(messagePreparator);
    }

    @Override
    @Transactional
    public Mail insert(Mail mail) {
        return mailRepository.addOrUpdate(mail);
    }

    @Override
    public Mail findByDate(LocalDate date) {
        return mailRepository.findByDate(date);
    }
}
