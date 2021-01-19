package com.pozharsky.dmitri.state.impl;

import com.pozharsky.dmitri.state.State;
import com.pozharsky.dmitri.entity.Terminal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StartState implements State {
    private static final Logger logger = LogManager.getLogger(StartState.class);

    @Override
    public void next(Terminal terminal) {
        terminal.setState(new ProcessState());
        logger.info("Process is started");
    }

    @Override
    public void previous(Terminal terminal) {
        throw new UnsupportedOperationException("The terminal is in its root state");
    }
}
