package de.qsmq.java11introduction.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@Service
public class FileStaticsService {

    private final String fileName = "testfile";

    public String theFirstNLinesStartingWithChar(int n, char c) throws IOException {
        String result = Files.lines(Paths.get(fileName))
                .filter(s -> s.charAt(0) == c)
                .map(s -> s.toUpperCase())
                .collect(Collectors.joining("\n"));;

        return result;
    }




}
