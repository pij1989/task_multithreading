package com.pozharsky.dmitri.creator;

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
        List<Integer> data = parser.parse(initialData);
        List<Wagon> wagons = new ArrayList<>();
        for (int i = 0; i < data.get(1); i++) {
            wagons.add(new Wagon(LogisticBase.getInstance()));
        }
        return wagons;
    }
}
