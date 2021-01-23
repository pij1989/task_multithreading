package com.pozharsky.dmitri.entity;

import com.pozharsky.dmitri.entity.impl.PrepareOperationState;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public class Terminal {
    private Queue<Cargo> cargoList = new LinkedList<>();
    private State state = new PrepareOperationState();
    private Wagon wagon;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Wagon getWagon() {
        return wagon;
    }

    public void setWagon(Wagon wagon) {
        this.wagon = wagon;
    }

    public void handle() {
        state.handle(this);
    }

    public void cancel() {
        state.toCancel(this);
    }

    public void addCargo(Cargo cargo) {
        cargoList.add(cargo);
    }

    public Optional<Cargo> getCargo() {
        Cargo cargo = cargoList.poll();
        return Optional.ofNullable(cargo);
    }
}
