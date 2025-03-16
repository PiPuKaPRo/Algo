public class KMP {
    public static int[] prefixBorderArray(String pattern) {
        int n = pattern.length();
        int[] bp = new int[n];
        bp[0] = 0;
        for (int i = 1; i < n; i++) {
            int bpRight = bp[i - 1];
            while (bpRight > 0 && pattern.charAt(i) != pattern.charAt(bpRight)) {
                bpRight = bp[bpRight - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(bpRight)) {
                bp[i] = bpRight + 1;
            } else {
                bp[i] = 0;
            }
        }
        return bp;
    }

    private static int[] BPtoBPM(int[] bp, int m) {
        int[] bpm = new int[m];
        bpm[m - 1] = bp[m - 1];
        for (int i = 0; i < m - 1; i++) {
            if (bp[i] != 0 && (i + 1 < m && bp[i] + 1 == bp[i + 1])) {
                bpm[i] = bpm[bp[i] - 1];
            } else {
                bpm[i] = bp[i];
            }
        }
        return bpm;
    }

    public static void KMP(String pattern, String text) {
        int[] bp = prefixBorderArray(pattern);
        int m = pattern.length();
        int[] bpm = BPtoBPM(bp, m);
        int n = text.length();
        int k = 0;
        for (int i = 0; i < n; i++) {
            while (k > 0 && pattern.charAt(k) != text.charAt(i)) {
                k = bpm[k - 1];
            }
            if (pattern.charAt(k) == text.charAt(i)) {
                k++;
            }
            if (k == m) {
                System.out.println("Вхождение с позиции " + (i - k + 1));
                k = bpm[k - 1];
            }
        }
    }

    public static void main(String[] args) {
        String text = "abcabeabcabcabd";
        String pattern = "abcabd";
        KMP(pattern, text);
    }
}