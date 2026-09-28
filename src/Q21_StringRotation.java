/** Q21: Is B a cyclic rotation of A? If so, B is a substring of A+A. */
public class Q21_StringRotation {

    static boolean isRotation(String a, String b) {
        return a != null && b != null && a.length() == b.length() && (a + a).contains(b);
    }

    public static void main(String[] args) {
        System.out.println(isRotation("abcd", "cdab")); // true
        System.out.println(isRotation("abcd", "acbd")); // false
    }
}
