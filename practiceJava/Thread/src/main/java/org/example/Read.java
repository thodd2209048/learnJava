package org.example;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Read {
    private final Path currentPath;

    public Read() {
        this.currentPath = Paths.get("D:\\Work\\test\\aptech-test");
    }

    @SneakyThrows
    public Integer sumSingleFile(Path targetPath){
        return Files.readAllLines(targetPath).stream()
                .mapToInt(Integer::parseInt)
                .sum();
    }

    @SneakyThrows
    public List<Integer> sumAll(){
       return Files.list(currentPath)
                .map(this::sumSingleFile)
                .toList();
    }
}
