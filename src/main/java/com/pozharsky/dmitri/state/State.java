package com.pozharsky.dmitri.state;

import com.pozharsky.dmitri.entity.Terminal;

public interface State {
    void next(Terminal terminal);

    void previous(Terminal terminal);
}
