package com.pozharsky.dmitri.creator;

import com.pozharsky.dmitri.entity.Cargo;

import java.util.ArrayList;
import java.util.List;

public class CargoCreator {
    private static final CargoCreator instance = new CargoCreator();

    private CargoCreator() {
    }

    public static CargoCreator getInstance() {
        return instance;
    }

    public List<Cargo> createCargoes(int amountCargo, int percentPerishableCargo) {
        int amountPerishableCargo = amountCargo * percentPerishableCargo / 100;
        List<Cargo> cargoes = new ArrayList<>();
        for (int i = 0; i < amountCargo; i++) {
            if (cargoes.size() <= amountPerishableCargo) {
                Cargo cargo = new Cargo(true);
                cargoes.add(cargo);
                continue;
            }
            Cargo cargo = new Cargo(false);
            cargoes.add(cargo);
        }
        return cargoes;
    }
}
