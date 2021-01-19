package com.pozharsky.dmitri.service;

import com.pozharsky.dmitri.entity.Terminal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LogisticBase {
    private static final Logger logger = LogManager.getLogger(LogisticBase.class);
    private static final int INITIAL_AMOUNT_TERMINAL = 5;
    private static LogisticBase instance;
    private static final Lock lockInstance = new ReentrantLock();
    private final Queue<Terminal> freeTerminal;
    private final Queue<Terminal> givenAwayTerminal;
    private final ReentrantLock lockTerminal;
    private final Condition notEmpty;
    private final Condition notFull;


    private LogisticBase() {
        freeTerminal = new ArrayDeque<>();
        givenAwayTerminal = new ArrayDeque<>();
        lockTerminal = new ReentrantLock(true);
        notEmpty = lockTerminal.newCondition();
        notFull = lockTerminal.newCondition();
        for (int i = 0; i < INITIAL_AMOUNT_TERMINAL; i++) {
            freeTerminal.add(new Terminal());
        }
    }

    public static LogisticBase getInstance() {
        if (instance == null) {
            lockInstance.lock();
            try {
                if (instance == null) {
                    instance = new LogisticBase();
                }
            } finally {
                lockInstance.unlock();
            }
        }
        return instance;
    }

    public Terminal getTerminal() {
        Terminal terminal = null;
        lockTerminal.lock();
        try {
            while (freeTerminal.isEmpty()) {
                logger.info("Wait terminal...");
                notEmpty.await();
            }
            terminal = freeTerminal.remove();
            givenAwayTerminal.add(terminal);
            notFull.signal();
        } catch (InterruptedException e) {
            logger.error(e);
        } finally {
            lockTerminal.unlock();
        }
        return terminal;
    }

    public void releaseTerminal(Terminal terminal){
        lockTerminal.lock();
        try {
            while (freeTerminal.size() == INITIAL_AMOUNT_TERMINAL){
                logger.info("Wait release...");
                notFull.await();
            }
            givenAwayTerminal.remove(terminal);
            freeTerminal.add(terminal);
            notEmpty.signal();
        } catch (InterruptedException e) {
            logger.error(e);
        } finally {
            lockTerminal.unlock();
        }
    }
}
