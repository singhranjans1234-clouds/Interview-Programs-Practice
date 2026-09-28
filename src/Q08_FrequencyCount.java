import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

/** Q8: Character / word frequency using groupingBy + counting. */
public class Q08_FrequencyCount {

    static Map<Character, Long> charFreq(String s) {
        return s.chars().mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
    }

    static Map<String, Long> wordFreq(String s) {
        return Arrays.stream(s.toLowerCase().split("\\s+"))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
    }

    public static void main(String[] args) {
        System.out.println(charFreq("programming"));
        System.out.println(wordFreq("the quick fox and The lazy fox"));
    }
}
