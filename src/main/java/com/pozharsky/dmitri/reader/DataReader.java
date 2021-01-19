package com.pozharsky.dmitri.reader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataReader {
    private static final Logger logger = LogManager.getLogger(DataReader.class);
    private static final String DEFAULT_FILE = "data\\data.txt";

    public String readData(String file) {
        if (file == null) {
            file = DEFAULT_FILE;
        }
        Path path = Path.of(file);
        try (Stream<String> lines = Files.lines(path)) {
            return lines.collect(Collectors.joining());
        } catch (IOException e) {
            logger.fatal("Error of file: " + e);
            throw new RuntimeException(e);
        }
    }
}
