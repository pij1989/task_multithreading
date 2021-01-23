package com.pozharsky.dmitri.entity.impl;

import com.pozharsky.dmitri.entity.State;
import com.pozharsky.dmitri.entity.Terminal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FinishOperationState implements State {
    private static final Logger logger = LogManager.getLogger(FinishOperationState.class);

    @Override
    public void handle(Terminal terminal) {
        terminal.setState(new PrepareOperationState());
        logger.info("Terminal is prepared to new operation");
    }

    @Override
    public void toCancel(Terminal terminal) {
        throw new UnsupportedOperationException("Operation is completed");
    }
}
