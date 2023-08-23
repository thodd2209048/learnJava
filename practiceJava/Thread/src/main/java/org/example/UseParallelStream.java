package org.example;

import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UseParallelStream {
    @SneakyThrows
    public static void main(String[] args) {
        Path currentPath = Paths.get("D:\\Work\\test\\aptech-test");
        List<Integer> result = new ArrayList<>();

        Files.list(currentPath)
                .parallel()
                .forEach(p -> {
                    try {
                        int sumResult = Files.readAllLines(p).stream()
                                .mapToInt(Integer::parseInt)
                                .sum();
                        result.add(sumResult);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        System.out.println(result);
    }
}
