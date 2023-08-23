package org.example;

import lombok.SneakyThrows;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.stream.Stream;

public class Create {
    private final Path currentPath;

    public Create() {
        this.currentPath = Paths.get("D:\\Work\\test\\aptech-test");
    }

    @SneakyThrows
    public void createExampleFile() {
        for (int i = 0; i < 10; i++) {
            Files.createFile(currentPath.resolve("test" + i + ".txt"));
        }
    }

    @SneakyThrows
    public void writeExampleContent(Path targetPath) {
        Double randomLine = Math.ceil(Math.random() * 10);

        BufferedWriter writer = Files.newBufferedWriter(targetPath, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        for (int i = 0; i < randomLine; i++) {
            String randomNumber = String.valueOf((int) Math.ceil(Math.random() * 10));
            writer.write(randomNumber);
            writer.newLine();
        }
        writer.close();

    }

    @SneakyThrows
    public void writeAllFile() {
        Stream<Path> files = Files.list(currentPath);
        files.forEach(this::writeExampleContent);
    }
}
