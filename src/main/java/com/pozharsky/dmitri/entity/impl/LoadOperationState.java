package com.pozharsky.dmitri.entity.impl;

import com.pozharsky.dmitri.entity.Cargo;
import com.pozharsky.dmitri.entity.State;
import com.pozharsky.dmitri.entity.Terminal;
import com.pozharsky.dmitri.entity.Wagon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class LoadOperationState implements State {
    private static final Logger logger = LogManager.getLogger(LoadOperationState.class);
    private static final long DURATION = 5L;

    @Override
    public void handle(Terminal terminal) {
        try {
            Optional<Cargo> optionalCargo = terminal.getCargo();
            if (optionalCargo.isPresent()) {
                Wagon wagon = terminal.getWagon();
                wagon.setCargo(optionalCargo);
                TimeUnit.SECONDS.sleep(DURATION);
                logger.info("Load operation is completed");
            }
        } catch (InterruptedException e) {
            logger.error(e);
        }
    }

    @Override
    public void toCancel(Terminal terminal) {
        throw new UnsupportedOperationException("Impossible to cancel unload operation which is started");
    }
}
