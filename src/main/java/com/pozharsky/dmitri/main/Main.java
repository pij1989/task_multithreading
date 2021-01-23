package com.pozharsky.dmitri.main;

import com.pozharsky.dmitri.comparator.WagonComparator;
import com.pozharsky.dmitri.creator.WagonCreator;
import com.pozharsky.dmitri.creator.WagonThreadCreator;
import com.pozharsky.dmitri.entity.Wagon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final String FILE = "data\\data.txt";
    private static final long DURATION = 1L;

    public static void main(String[] args) {
        WagonCreator creator = WagonCreator.getInstance();
        WagonThreadCreator wagonThreadCreator = WagonThreadCreator.getInstance();
        List<Wagon> wagons = creator.creatWagons(FILE);
        Queue<Wagon> wagonQueue = new PriorityQueue<>(WagonComparator.PERISHABLE);
        wagonQueue.addAll(wagons);
        for (Wagon wagon : wagonQueue) {
            Thread thread = wagonThreadCreator.newThread(wagon);
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(DURATION);
            } catch (InterruptedException e) {
                logger.error(e);
            }
        }
    }
}
