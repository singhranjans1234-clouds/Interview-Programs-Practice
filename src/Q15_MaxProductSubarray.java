/** Q15: Maximum product subarray - track both max and min (negatives flip them). */
public class Q15_MaxProductSubarray {

    static int maxProduct(int[] a) {
        int max = a[0], min = a[0], best = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] < 0) { int t = max; max = min; min = t; }
            max = Math.max(a[i], max * a[i]);
            min = Math.min(a[i], min * a[i]);
            best = Math.max(best, max);
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println(maxProduct(new int[]{2, 3, -2, 4}));   // 6
        System.out.println(maxProduct(new int[]{-2, 0, -1}));     // 0
        System.out.println(maxProduct(new int[]{-2, 3, -4}));     // 24
    }
}
