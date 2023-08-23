package org.example;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class FileExplorer  extends Thread{
    private Path path;

    public FileExplorer() {
        String os = System.getProperty("os.name").toLowerCase();
        path = os.contains("win")? Paths.get("D:\\") : Paths.get("/");
    }

    public List<File> list() {
        return Arrays.stream(path.toFile().listFiles()).toList();
    }

    public String enter(String name) {
        Path targetPath = path.resolve(name);
        if(!Files.exists(targetPath)) {
            throw new RuntimeException("File/Folder not found");
        } else if (Files.isDirectory(targetPath)) {
            path = targetPath;
            return null;
        } else if (Files.isRegularFile(targetPath)) {
            try {
                return Files.readString(targetPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return String.valueOf(path);
    }

    public void createFile(String name, String content) {
        Path filePath = path.resolve(name);
        try {
            Files.write(filePath, content.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createFolder(String name){
        Path folderPath = path.resolve(name);
        try {
            Files.createDirectories(folderPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(String name)  {
        Path targetPath = path.resolve(name);
        if(!Files.exists(targetPath)){
            throw new RuntimeException("File not found");
        }
        try {
            FileUtils.deleteDirectory(targetPath.toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void rename(String name, String newName){
        Path oldPath = path.resolve(name);
        Path newPath = path.resolve(newName);
        if(!Files.exists(oldPath)){
            throw new RuntimeException("File not found");
        }
        try {
            Files.move(oldPath, newPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
