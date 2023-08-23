package org.example;

import lombok.Getter;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Calculate extends Thread {
    @Getter
    private static List<Integer> result = new ArrayList<>();
    private final Path targetPath;
    @Getter
    private Integer sumResult;

    public Calculate(Path targetPath) {
        this.targetPath = targetPath;
    }

    @SneakyThrows
    @Override
    public void run() {

        sleep((int) Math.ceil(Math.random() * 1000));
        sumResult = Files.readAllLines(targetPath).stream()
                .mapToInt(Integer::parseInt)
                .sum();
        synchronized (result) {
            result.add(sumResult);
        }
    }
}
