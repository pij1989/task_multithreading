package com.pozharsky.dmitri;

import com.pozharsky.dmitri.creator.WagonCreator;
import com.pozharsky.dmitri.entity.Wagon;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final String FILE = "data\\data.txt";

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        WagonCreator creator = WagonCreator.getInstance();
        List<Wagon> wagons = creator.creatWagons(FILE);
        for (Wagon wagon : wagons) {
            executorService.execute(wagon);
        }
        executorService.shutdown();
    }
}
