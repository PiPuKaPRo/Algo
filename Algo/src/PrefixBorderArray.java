public class PrefixBorderArray {
    public static int[] prefixBorderArray(String S) {
        int n = S.length();
        int[] bp = new int[n];
        bp[0] = 0;
        for (int i = 1; i < n; i++) {
            int bpRight = bp[i - 1];
            while (bpRight != 0 && S.charAt(i) != S.charAt(bpRight)) {
                bpRight = bp[bpRight - 1];
            }
            if (S.charAt(i) == S.charAt(bpRight)) {
                bp[i] = bpRight + 1;
            } else {
                bp[i] = 0;
            }
        }
        return bp;
    }

    public static void main(String[] args) {
        String example = "ABAABASCBAABAAAB";
        int[] result = prefixBorderArray(example);
        for (int val : result) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
}