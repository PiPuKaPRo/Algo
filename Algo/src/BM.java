import java.util.ArrayList;
import java.util.List;

public class BM {
    private static List<List<Integer>> positionList(String S, char[] alphabet) {
        int m = S.length();
        List<List<Integer>> pl = new ArrayList<>();
        for (char c : alphabet) {
            pl.add(new ArrayList<>());
        }
        for (int k = m - 1; k >= 0; k--) {
            char c = S.charAt(k);
            int index = c - 'A';
            if (index >= 0 && index < alphabet.length) {
                pl.get(index).add(k);
            }
        }
        return pl;
    }

    private static int badCharShift(List<List<Integer>> pl, char[] alphabet, char badChar, int posBad) {
        if (posBad < 0) return 1;
        int index = badChar - 'A';
        if (index < 0 || index >= alphabet.length) return posBad + 1;
        List<Integer> positions = pl.get(index);
        for (int pos : positions) {
            if (pos < posBad) return posBad - pos;
        }
        return posBad + 1;
    }

    public static void BM(String pattern, String text) {
        char[] alphabet = new char[26];
        for (int i = 0; i < 26; i++) {
            alphabet[i] = (char) ('A' + i);
        }
        List<List<Integer>> pl = positionList(pattern, alphabet);
        int m = pattern.length();
        int n = text.length();
        int currentEnd = m;
        while (currentEnd <= n) {
            int k = m - 1;
            int i = currentEnd - 1;
            while (k >= 0 && text.charAt(i) == pattern.charAt(k)) {
                k--;
                i--;
            }
            if (k < 0) {
                System.out.println("Вхождение с позиции " + (i + 1));
                currentEnd++;
            } else {
                char badChar = text.charAt(i);
                int shift = badCharShift(pl, alphabet, badChar, k);
                currentEnd += shift;
            }
        }
    }

    public static void main(String[] args) {
        String s1 = "ABCBCBCCDBABDFSB";
        String s2 = "BCBC";
        BM(s2, s1);
    }
}