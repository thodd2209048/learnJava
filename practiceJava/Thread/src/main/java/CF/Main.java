package CF;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;

import Thread.ReadFile;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Path> files = new CopyOnWriteArrayList<>(
                List.of(Paths.get("file1.txt"),
                        Paths.get("file2.txt"),
                        Paths.get("file3.txt")
                )
        );

        List<Integer> sums = new CopyOnWriteArrayList<>();
        CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(()->{
            ReadFile.calculateSum(files, sums);
        });
        CompletableFuture<Void> completableFuture2 = CompletableFuture.runAsync(()->{
            ReadFile.calculateSum(files, sums);
        });

        completableFuture1.get();
        completableFuture2.get();

        System.out.println(sums);
    }
}
