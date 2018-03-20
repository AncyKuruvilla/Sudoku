package com.game.email;

/**
 * Created by jino-ancy on 20-03-2018.
 */
public interface MailService {
    void send(String to, String subject, String text);
}
