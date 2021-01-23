package com.pozharsky.dmitri.entity;

import java.util.Optional;

public class Wagon implements Runnable {
    private long id;
    private LogisticBase logisticBase;
    private Optional<Cargo> cargo;

    public Wagon(long id, LogisticBase logisticBase, Optional<Cargo> cargo) {
        this.id = id;
        this.logisticBase = logisticBase;
        this.cargo = cargo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Optional<Cargo> getCargo() {
        return cargo;
    }

    public void setCargo(Optional<Cargo> cargo) {
        this.cargo = cargo;
    }

    @Override
    public void run() {
        Terminal terminal = logisticBase.getTerminal();
        terminal.setWagon(this);
        terminal.handle();
        terminal.handle();
        terminal.handle();
        logisticBase.releaseTerminal(terminal);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Wagon wagon = (Wagon) o;

        if (id != wagon.id) return false;
        if (logisticBase != null ? !logisticBase.equals(wagon.logisticBase) : wagon.logisticBase != null) return false;
        return cargo.isPresent() ? cargo.equals(wagon.cargo) : wagon.cargo.isEmpty();
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (logisticBase != null ? logisticBase.hashCode() : 0);
        result = 31 * result + (cargo.isPresent() ? cargo.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Wagon{");
        sb.append("id=").append(id);
        sb.append(", cargo=").append(cargo);
        sb.append('}');
        return sb.toString();
    }
}
