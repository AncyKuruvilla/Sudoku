package com.game.sudoku.scheduler;

/**
 * Email Scheduler.
 */
public interface EmailScheduler {
    /**
     * Sends puzzle at scheduled time.
     */
    void sendPuzzel();

    /**
     * Sends solution at scheduled time.
     */
    void sendSolution();
}
