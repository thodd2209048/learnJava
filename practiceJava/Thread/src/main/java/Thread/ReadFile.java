package Thread;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@AllArgsConstructor
public class ReadFile {
    private Path path;
    private List<Integer> sums;

    public static Integer sumAFile(Path targetPath) throws IOException {
        return Files.readAllLines(targetPath).stream()
                .mapToInt(Integer::parseInt)
                .sum();
    }

    public static void calculateSum(List<Path> files, List<Integer> sums){
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
