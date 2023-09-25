package FindtheDifference389;

import java.util.stream.IntStream;

public class Solution {

    public char findTheDifference(String s, String t) {
        IntStream sStream = s.chars();
        IntStream tStream = t.chars();

        int difference = tStream.sum() - sStream.sum();
        return (char) difference;
    }
}
