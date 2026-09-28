import java.util.*;

/** Q7: Longest substring without repeating chars - sliding window, O(n). */
public class Q07_LongestSubstringNoRepeat {

    static int lengthOfLongest(String s) {
        Map<Character, Integer> last = new HashMap<>();
        int best = 0, left = 0;
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (last.containsKey(c) && last.get(c) >= left) left = last.get(c) + 1;
            last.put(c, right);
            best = Math.max(best, right - left + 1);
        }
        return best;
    }

    public static void main(String[] args) {
        for (String s : new String[]{"abcabcbb", "bbbbb", "pwwkew", ""})
            System.out.println("\"" + s + "\" -> " + lengthOfLongest(s));
    }
}
