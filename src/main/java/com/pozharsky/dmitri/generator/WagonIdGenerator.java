package com.pozharsky.dmitri.generator;

public class WagonIdGenerator {
    private static long count = 1;

    private WagonIdGenerator() {
    }

    public static long generate() {
        return count++;
    }
}
