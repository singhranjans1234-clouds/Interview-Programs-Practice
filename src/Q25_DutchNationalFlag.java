import java.util.*;

/** Q25: Sort 0s, 1s, 2s in one pass, in place (Dijkstra's Dutch National Flag). */
public class Q25_DutchNationalFlag {

    static void sort012(int[] a) {
        int low = 0, mid = 0, high = a.length - 1;
        while (mid <= high) {
            switch (a[mid]) {
                case 0 -> swap(a, low++, mid++);
                case 1 -> mid++;
                default -> swap(a, mid, high--); // don't advance mid: swapped value is unseen
            }
        }
    }

    static void swap(int[] a, int i, int j) { int t = a[i]; a[i] = a[j]; a[j] = t; }

    public static void main(String[] args) {
        int[] a = {2, 0, 2, 1, 1, 0, 0, 2, 1};
        sort012(a);
        System.out.println(Arrays.toString(a));
    }
}
