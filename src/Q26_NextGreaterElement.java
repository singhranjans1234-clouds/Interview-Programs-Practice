import java.util.*;

/** Q26: Next greater element to the right using a monotonic (decreasing) stack, O(n). */
public class Q26_NextGreaterElement {

    static int[] nextGreater(int[] a) {
        int[] res = new int[a.length];
        Arrays.fill(res, -1);
        Deque<Integer> stack = new ArrayDeque<>(); // indices whose next greater is still unknown
        for (int i = 0; i < a.length; i++) {
            while (!stack.isEmpty() && a[stack.peek()] < a[i]) res[stack.pop()] = a[i];
            stack.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(nextGreater(new int[]{4, 5, 2, 25})));      // [5, 25, 25, -1]
        System.out.println(Arrays.toString(nextGreater(new int[]{13, 7, 6, 12})));     // [-1, 12, 12, -1]
    }
}
