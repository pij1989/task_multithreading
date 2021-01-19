package com.pozharsky.dmitri.entity;

import com.pozharsky.dmitri.service.LogisticBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class Wagon implements Runnable {
    private static final Logger logger = LogManager.getLogger(Wagon.class);
    private LogisticBase logisticBase;

    public Wagon(LogisticBase logisticBase) {
        this.logisticBase = logisticBase;
    }

    @Override
    public void run() {
        try {
            Terminal terminal = logisticBase.getTerminal();
            terminal.nextState();
            TimeUnit.SECONDS.sleep(2);
            terminal.nextState();
            terminal.nextState();
            logisticBase.releaseTerminal(terminal);
        } catch (InterruptedException e) {
            logger.error(e);
        }
    }
}
