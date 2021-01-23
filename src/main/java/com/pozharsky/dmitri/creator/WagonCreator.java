package com.pozharsky.dmitri.creator;

import com.pozharsky.dmitri.entity.Cargo;
import com.pozharsky.dmitri.entity.LogisticBase;
import com.pozharsky.dmitri.entity.Wagon;
import com.pozharsky.dmitri.generator.WagonIdGenerator;
import com.pozharsky.dmitri.parser.DataParser;
import com.pozharsky.dmitri.reader.DataReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WagonCreator {
    private static final WagonCreator instance = new WagonCreator();

    private WagonCreator() {
    }

    public static WagonCreator getInstance() {
        return instance;
    }

    public List<Wagon> creatWagons(String fileData) {
        DataReader reader = new DataReader();
        String initialData = reader.readData(fileData);
        DataParser parser = DataParser.getInstance();
        List<Integer> data = parser.parse(initialData);
        int amountWagon = data.get(0);
        int percentPerishableCargo = data.get(1);
        int percentFullWagon = data.get(2);
        int amountFullWagon = amountWagon * percentFullWagon / 100;
        CargoCreator cargoCreator = CargoCreator.getInstance();
        List<Cargo> cargoes = cargoCreator.createCargoes(amountFullWagon, percentPerishableCargo);
        List<Wagon> wagons = new ArrayList<>();
        LogisticBase logisticBase = LogisticBase.getInstance();
        for (int i = 0; i < amountWagon; i++) {
            if (i < cargoes.size()) {
                Wagon wagon = new Wagon(WagonIdGenerator.generate(), logisticBase, Optional.of(cargoes.get(i)));
                wagons.add(wagon);
                continue;
            }
            wagons.add(new Wagon(WagonIdGenerator.generate(), logisticBase, Optional.empty()));
        }
        return wagons;
    }
}
