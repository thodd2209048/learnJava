package Thread;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<Path> files = new CopyOnWriteArrayList<>(
                List.of(Paths.get("file1.txt"),
                        Paths.get("file2.txt"),
                        Paths.get("file3.txt")
                )
        );

        List<Integer> sums = new CopyOnWriteArrayList<>();

        SumThread sumThread1 = new SumThread(files, sums);
        SumThread sumThread2 = new SumThread(files, sums);
        SumThread sumThread3 = new SumThread(files, sums);

        sumThread1.start();
        sumThread2.start();
        sumThread3.start();

        sumThread1.join();
        sumThread2.join();
        sumThread3.join();

        System.out.println(sums);
    }
}
