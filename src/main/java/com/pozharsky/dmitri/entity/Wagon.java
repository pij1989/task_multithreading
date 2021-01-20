package com.pozharsky.dmitri.entity;

import com.pozharsky.dmitri.service.LogisticBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class Wagon implements Runnable {
    private static final Logger logger = LogManager.getLogger(Wagon.class);
    private LogisticBase logisticBase;
    private Cargo cargo;

    public Wagon(LogisticBase logisticBase, Cargo cargo) {
        this(logisticBase);
        this.cargo = cargo;
    }

    public Wagon(LogisticBase logisticBase) {
        this.logisticBase = logisticBase;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    @Override
    public void run() {
        try {
            logger.debug("Cargo: " + cargo);
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


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Wagon{");
        sb.append("cargo=").append(cargo);
        sb.append('}');
        return sb.toString();
    }
}
