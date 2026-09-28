import java.util.*;
import java.util.stream.*;

/** Q2: Group anagrams. Key = sorted characters. */
public class Q02_GroupAnagrams {

    static List<List<String>> withCollections(String[] words) {
        Map<String, List<String>> map = new HashMap<>();
        for (String w : words) {
            char[] c = w.toCharArray();
            Arrays.sort(c);
            map.computeIfAbsent(new String(c), k -> new ArrayList<>()).add(w);
        }
        return new ArrayList<>(map.values());
    }

    static List<List<String>> withStreams(String[] words) {
        return new ArrayList<>(Arrays.stream(words)
                .collect(Collectors.groupingBy(w -> {
                    char[] c = w.toCharArray();
                    Arrays.sort(c);
                    return new String(c);
                })).values());
    }

    public static void main(String[] args) {
        String[] in = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(withCollections(in));
        System.out.println(withStreams(in));
    }
}
