package com.pozharsky.dmitri.entity;

import com.pozharsky.dmitri.state.State;
import com.pozharsky.dmitri.state.impl.StartState;

public class Terminal {
    private State state = new StartState();

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void previousState() {
        state.previous(this);
    }

    public void nextState() {
        state.next(this);
    }
}
