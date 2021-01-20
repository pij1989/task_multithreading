package com.pozharsky.dmitri.entity;

public class Cargo {
    boolean isPerishable;

    public Cargo(boolean isPerishable) {
        this.isPerishable = isPerishable;
    }

    public boolean isPerishable() {
        return isPerishable;
    }

    public void setPerishable(boolean perishable) {
        isPerishable = perishable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cargo cargo = (Cargo) o;

        return isPerishable == cargo.isPerishable;
    }

    @Override
    public int hashCode() {
        return (isPerishable ? 1 : 0);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Cargo{");
        sb.append("isPerishable=").append(isPerishable);
        sb.append('}');
        return sb.toString();
    }
}
