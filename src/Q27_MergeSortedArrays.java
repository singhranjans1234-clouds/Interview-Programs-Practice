import java.util.*;

/** Q27: Merge two sorted arrays. (a) O(n+m) two-pointer, (b) in-place gap method with no extra array. */
public class Q27_MergeSortedArrays {

    // (a) O(n+m) time, O(n+m) output array
    static int[] merge(int[] a, int[] b) {
        int[] r = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;
        while (i < a.length && j < b.length) r[k++] = a[i] <= b[j] ? a[i++] : b[j++];
        while (i < a.length) r[k++] = a[i++];
        while (j < b.length) r[k++] = b[j++];
        return r;
    }

    // (b) In-place: after this, a holds the smallest n elements and b the largest m, both sorted.
    // O((n+m) log(n+m)) time, O(1) extra space.
    static void mergeInPlace(int[] a, int[] b) {
        int n = a.length, m = b.length;
        for (int gap = nextGap(n + m); gap > 0; gap = nextGap(gap)) {
            for (int i = 0; i + gap < n + m; i++) {
                int j = i + gap;
                int x = i < n ? a[i] : b[i - n];
                int y = j < n ? a[j] : b[j - n];
                if (x > y) {
                    if (i < n) a[i] = y; else b[i - n] = y;
                    if (j < n) a[j] = x; else b[j - n] = x;
                }
            }
        }
    }

    private static int nextGap(int gap) { return gap <= 1 ? 0 : (gap + 1) / 2; }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(merge(new int[]{1, 3, 5, 7}, new int[]{0, 2, 6, 8, 9})));
        int[] a = {1, 5, 9, 10, 15, 20}, b = {2, 3, 8, 13};
        mergeInPlace(a, b);
        System.out.println(Arrays.toString(a) + " " + Arrays.toString(b));
    }
}
