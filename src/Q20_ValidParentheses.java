import java.util.*;

/** Q20: Valid parentheses using a stack. */
public class Q20_ValidParentheses {

    static boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            switch (c) {
                case '(' -> stack.push(')');
                case '{' -> stack.push('}');
                case '[' -> stack.push(']');
                default -> { if (stack.isEmpty() || stack.pop() != c) return false; }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        for (String s : new String[]{"()[]{}", "([{}])", "(]", "((", "]"})
            System.out.println(s + " -> " + isValid(s));
    }
}
