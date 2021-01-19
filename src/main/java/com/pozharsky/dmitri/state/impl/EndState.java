package com.pozharsky.dmitri.state.impl;

import com.pozharsky.dmitri.state.State;
import com.pozharsky.dmitri.entity.Terminal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class EndState implements State {
    private static final Logger logger = LogManager.getLogger(EndState.class);

    @Override
    public void next(Terminal terminal) {
        terminal.setState(new StartState());
        logger.info("The terminal prepared to new process");
    }

    @Override
    public void previous(Terminal terminal) {
        terminal.setState(new ProcessState());
        logger.info("The process can't completed");
    }
}

