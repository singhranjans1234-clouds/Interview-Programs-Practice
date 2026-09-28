import java.util.*;
import java.util.stream.*;

/** Q1: First non-repeating character (case-insensitive, so "Swiss" -> 'w'). */
public class Q01_FirstNonRepeatingChar {

    // Loop version: O(n) using a frequency map that keeps insertion order.
    static Character withLoops(String s) {
        String lower = s.toLowerCase();
        Map<Character, Integer> freq = new LinkedHashMap<>();
        for (char c : lower.toCharArray()) freq.merge(c, 1, Integer::sum);
        for (int i = 0; i < lower.length(); i++)
            if (freq.get(lower.charAt(i)) == 1) return s.charAt(i);
        return null;
    }

    // Stream version: index of first char whose first and last occurrence are the same.
    static Character withStreams(String s) {
        String lower = s.toLowerCase();
        return IntStream.range(0, s.length())
                .filter(i -> lower.indexOf(lower.charAt(i)) == lower.lastIndexOf(lower.charAt(i)))
                .mapToObj(s::charAt)
                .findFirst()
                .orElse(null);
    }

    public static void main(String[] args) {
        for (String s : new String[]{"Swiss", "aabb", "leetcode"})
            System.out.println(s + " -> loop=" + withLoops(s) + ", stream=" + withStreams(s));
    }
}
