package com.pozharsky.dmitri.creator;

import com.pozharsky.dmitri.entity.Cargo;
import com.pozharsky.dmitri.entity.Wagon;
import com.pozharsky.dmitri.parser.DataParser;
import com.pozharsky.dmitri.reader.DataReader;
import com.pozharsky.dmitri.service.LogisticBase;

import java.util.ArrayList;
import java.util.List;

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
        List<String> data = parser.parse(initialData);
        List<Wagon> wagons = new ArrayList<>();
        int amount = Integer.parseInt(data.get(0));
        for (int i = 0; i < amount; i++) {
            Cargo cargo = new Cargo(Boolean.parseBoolean(data.get(i + 1)));
            wagons.add(new Wagon(LogisticBase.getInstance(), cargo));
        }
        return wagons;
    }
}
