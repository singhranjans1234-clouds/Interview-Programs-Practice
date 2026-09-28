import java.util.*;

/** Q12: Move zeros to the end (or start) in-place, keeping order of non-zeros. */
public class Q12_MoveZeros {

    static void zerosToRight(int[] a) {
        int pos = 0;
        for (int x : a) if (x != 0) a[pos++] = x;
        while (pos < a.length) a[pos++] = 0;
    }

    static void zerosToLeft(int[] a) {
        int pos = a.length - 1;
        for (int i = a.length - 1; i >= 0; i--) if (a[i] != 0) a[pos--] = a[i];
        while (pos >= 0) a[pos--] = 0;
    }

    public static void main(String[] args) {
        int[] a = {2, 3, 0, 1, 0, 1, 2, 0, 6};
        int[] b = a.clone();
        zerosToRight(a); zerosToLeft(b);
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
    }
}
