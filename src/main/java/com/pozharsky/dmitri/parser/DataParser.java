package com.pozharsky.dmitri.parser;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DataParser {
    private static final String COMMA_DELIMITER = ",";
    private static final DataParser instance = new DataParser();

    private DataParser() {
    }

    public static DataParser getInstance() {
        return instance;
    }

    public List<Integer> parse(String initialData) {
        String[] data = initialData.split(COMMA_DELIMITER);
        return Arrays.stream(data)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
