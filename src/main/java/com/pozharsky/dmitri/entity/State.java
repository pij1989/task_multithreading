package com.pozharsky.dmitri.entity;

public interface State {
    void handle(Terminal terminal);

    void toCancel(Terminal terminal);
}
