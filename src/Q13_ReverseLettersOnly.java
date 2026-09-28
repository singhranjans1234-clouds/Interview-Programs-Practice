/** Q13: Reverse only letters; digits and special characters keep their positions. */
public class Q13_ReverseLettersOnly {

    static String reverseLetters(String s) {
        char[] c = s.toCharArray();
        int i = 0, j = c.length - 1;
        while (i < j) {
            if (!Character.isLetter(c[i])) i++;
            else if (!Character.isLetter(c[j])) j--;
            else { char t = c[i]; c[i++] = c[j]; c[j--] = t; }
        }
        return new String(c);
    }

    public static void main(String[] args) {
        System.out.println(reverseLetters("a1b2c3d4e5f"));  // f1e2d3c4b5a
        System.out.println(reverseLetters("Ab-cD$ef"));
    }
}
