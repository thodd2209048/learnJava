package Thread;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@AllArgsConstructor
public class SumThread extends Thread{
    private List<Path> files;
    private List<Integer> sums;

    @Override
    public void run() {
        while (!files.isEmpty()){
            Path currentPath = files.remove(0);
            try {
                sums.add(ReadFile.sumAFile(currentPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
