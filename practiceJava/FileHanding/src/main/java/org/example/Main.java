package org.example;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FileExplorer fileExplorer = new FileExplorer();
        Scanner sc = new Scanner(System.in);
//        fileExplorer.createFile("test.txt", "123");
//        fileExplorer.createFolder("thung rac");

//        fileExplorer.delete("thung rac");
        for (File file: fileExplorer.list()
             ) {
            System.out.println(file);
        }
    }
}