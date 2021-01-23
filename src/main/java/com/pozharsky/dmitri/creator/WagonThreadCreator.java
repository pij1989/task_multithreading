package com.pozharsky.dmitri.creator;

import com.pozharsky.dmitri.entity.Cargo;
import com.pozharsky.dmitri.entity.Wagon;

import java.util.Optional;

public class WagonThreadCreator {
    private static final WagonThreadCreator instance = new WagonThreadCreator();

    private WagonThreadCreator() {
    }

    public static WagonThreadCreator getInstance() {
        return instance;
    }

    public Thread newThread(Wagon wagon) {
        StringBuilder builder = new StringBuilder();
        Thread thread = new Thread(wagon);
        long id = wagon.getId();
        Optional<Cargo> optionalCargo = wagon.getCargo();
        builder.append("Wagon ");
        builder.append(id);
        if (optionalCargo.isPresent()) {
            Cargo cargo = optionalCargo.get();
            builder.append(" is perishable: ");
            builder.append(cargo.isPerishable());
            thread.setName(builder.toString());
            return thread;
        } else {
            builder.append(" is empty");
            thread.setName(builder.toString());
            return thread;
        }
    }
}
