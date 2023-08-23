package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Path currentPath = Paths.get("D:\\Work\\test\\aptech-test");
        List<Calculate> threadList = new ArrayList<>();

        Files.list(currentPath)
                .forEach(p -> {
                    Calculate calc = new Calculate(p);
                    threadList.add(calc);
                    calc.start();
                });

        threadList.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println(Calculate.getResult());



//        result.stream().forEach(System.out::println);


//        Create create = new Create();
//        create.writeAllFile();

//        Khong dung thread
//        Read read = new Read();
//        List<Integer> result = read.sumAll();
//        for (Integer number : result
//        ) {
//            System.out.println(number);
//        }
    }
}