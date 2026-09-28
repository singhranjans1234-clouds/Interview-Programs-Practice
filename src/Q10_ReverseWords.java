import java.util.*;
import java.util.stream.*;

/** Q10: Reverse each word, or reverse the order of words. */
public class Q10_ReverseWords {

    static String reverseEachWord(String s) {
        return Arrays.stream(s.split(" "))
                .map(w -> new StringBuilder(w).reverse().toString())
                .collect(Collectors.joining(" "));
    }

    static String reverseWordOrder(String s) {
        List<String> words = Arrays.asList(s.trim().split("\\s+"));
        Collections.reverse(words);
        return String.join(" ", words);
    }

    static String reverseWordOrderLoop(String s) {
        String[] w = s.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = w.length - 1; i >= 0; i--) sb.append(w[i]).append(i > 0 ? " " : "");
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(reverseEachWord("Hello world"));     // olleH dlrow
        System.out.println(reverseWordOrder("Hello world"));    // world Hello
        System.out.println(reverseWordOrderLoop("Hello world"));
    }
}
