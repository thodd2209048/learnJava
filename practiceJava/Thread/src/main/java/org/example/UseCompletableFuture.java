package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class UseCompletableFuture {
    public static void main(String[] args) throws IOException {
        Path currentPath = Paths.get("D:\\Work\\test\\aptech-test");
        List<Integer> result = new ArrayList<>();
        List<CompletableFuture<Integer>> completableFutures = new ArrayList<>();

        Files.list(currentPath).forEach(p -> {
           CompletableFuture<Integer> future = CompletableFuture.supplyAsync(()->{
                try {
                   return Files.readAllLines(p).stream().mapToInt(Integer::parseInt).sum();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            completableFutures.add(future);
        });

        CompletableFuture<Void> allOf = CompletableFuture.allOf(
                completableFutures.toArray(new CompletableFuture[completableFutures.size()])
        );

        allOf.thenRun(()->{
            for (CompletableFuture<Integer> future: completableFutures
                 ) {
                try {
                    result.add(future.get());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
        }).join();

        System.out.println(result);
    }
}