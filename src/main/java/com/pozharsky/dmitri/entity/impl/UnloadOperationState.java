package com.pozharsky.dmitri.entity.impl;

import com.pozharsky.dmitri.entity.Cargo;
import com.pozharsky.dmitri.entity.State;
import com.pozharsky.dmitri.entity.Terminal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class UnloadOperationState implements State {
    private static final Logger logger = LogManager.getLogger(UnloadOperationState.class);
    private static final long DURATION = 5L;

    @Override
    public void handle(Terminal terminal) {
        try {
            Optional<Cargo> optionalCargo = terminal.getWagon().getCargo();
            if (optionalCargo.isPresent()) {
                terminal.addCargo(optionalCargo.get());
                TimeUnit.SECONDS.sleep(DURATION);
                terminal.setState(new FinishOperationState());
                logger.info("Unload operation is completed");
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
