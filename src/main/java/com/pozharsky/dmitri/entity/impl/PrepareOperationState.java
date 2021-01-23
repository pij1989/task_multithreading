package com.pozharsky.dmitri.entity.impl;

import com.pozharsky.dmitri.entity.State;
import com.pozharsky.dmitri.entity.Terminal;
import com.pozharsky.dmitri.entity.Wagon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PrepareOperationState implements State {
    private static final Logger logger = LogManager.getLogger(PrepareOperationState.class);

    @Override
    public void handle(Terminal terminal) {
        Wagon wagon = terminal.getWagon();
        if (wagon != null) {
            if (wagon.getCargo().isPresent()) {
                terminal.setState(new UnloadOperationState());
                logger.info("Unload begin");
            } else {
                terminal.setState(new LoadOperationState());
                logger.info("Load begin");
            }
        } else {
            this.toCancel(terminal);
        }
    }

    @Override
    public void toCancel(Terminal terminal) {
        terminal.setState(new CancelledState());
        logger.info("Operation is interrupted");
    }
}
