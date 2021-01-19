package com.pozharsky.dmitri.state.impl;

import com.pozharsky.dmitri.state.State;
import com.pozharsky.dmitri.entity.Terminal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProcessState implements State {
    private static final Logger logger = LogManager.getLogger(ProcessState.class);

    @Override
    public void next(Terminal terminal) {
        terminal.setState(new EndState());
        logger.info("Process is successfully finished");
    }

    @Override
    public void previous(Terminal terminal) {
        terminal.setState(new StartState());
        logger.info("Process is failed. Try start again");
    }
}
