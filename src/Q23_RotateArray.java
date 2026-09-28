import java.util.*;

/** Q23: Rotate array by k using the three-reversal trick: O(n) time, O(1) space. */
public class Q23_RotateArray {

    static void rotateRight(int[] a, int k) {
        int n = a.length;
        if (n == 0) return;
        k %= n;
        reverse(a, 0, n - 1);
        reverse(a, 0, k - 1);
        reverse(a, k, n - 1);
    }

    static void rotateLeft(int[] a, int k) {
        if (a.length == 0) return;
        rotateRight(a, a.length - (k % a.length));
    }

    static void reverse(int[] a, int i, int j) {
        while (i < j) { int t = a[i]; a[i++] = a[j]; a[j--] = t; }
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6, 7};
        rotateRight(a, 3);   System.out.println(Arrays.toString(a)); // [5, 6, 7, 1, 2, 3, 4]
        int[] b = {1, 2, 3, 4, 5, 6, 7};
        rotateLeft(b, 3);    System.out.println(Arrays.toString(b)); // [4, 5, 6, 7, 1, 2, 3]
        int[] c = {1, 2, 3};
        rotateRight(c, 10);  System.out.println(Arrays.toString(c)); // k > n
    }
}
