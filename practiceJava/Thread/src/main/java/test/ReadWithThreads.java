package test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadWithThreads extends Thread {
    private final Path targetPath;
    private Integer sumResult;

    public ReadWithThreads(Path targetPath) {
        this.targetPath = targetPath;
    }

    @Override
    public void run() {
        try {
            sumResult = Files.readAllLines(targetPath)
                    .stream()
                    .mapToInt(Integer::parseInt)
                    .sum();
        } catch (Exception e) {
            sumResult = 0; // Handle exception appropriately
        }
    }

    public Integer getSumResult() {
        return sumResult;
    }

    public static void main(String[] args) throws InterruptedException {
        Path currentPath = Paths.get("D:\\Work\\test\\aptech-test");
        List<ReadWithThreads> threadList = new ArrayList<>();

        try {
            Files.list(currentPath)
                    .forEach(path -> {
                        ReadWithThreads thread = new ReadWithThreads(path);
                        threadList.add(thread);
                        thread.start();
                    });

            for (ReadWithThreads thread : threadList) {
                thread.join();
                System.out.println("Tổng từ tệp " + thread.targetPath.getFileName() + ": " + thread.getSumResult());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

