package Parallel;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import Thread.ReadFile;

public class Main {
    public static void main(String[] args) {
        List<Path> files = new CopyOnWriteArrayList<>(
                List.of(Paths.get("file1.txt"),
                        Paths.get("file2.txt"),
                        Paths.get("file3.txt")
                )
        );

        List<Integer> sums = new CopyOnWriteArrayList<>();

        files.stream().parallel()
                .forEach(s -> {
                    try {
                        sums.add(ReadFile.sumAFile(s));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        System.out.println(sums);
    }
}
