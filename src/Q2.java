public class Q2 {

    static int countOccurrences(char c, String pattern) {
        int count = 0;

        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) == c)
                count++;
        }

        return count;
    }

    public static void search(String text, String pattern) {

        int n = text.length();
        int m = pattern.length();

        int shift = 0;

        while (shift <= n - m) {

            int j = m - 1;

            // Compare from right to left
            while (j >= 0 &&
                    pattern.charAt(j) ==
                            text.charAt(shift + j)) {
                j--;
            }

            // Pattern found
            if (j < 0) {

                System.out.println();
                System.out.println(
                        "Pattern matched at index "
                                + shift);

                // Continue searching
                shift += m;

            } else {

                char mismatch =
                        text.charAt(shift + j);

                int badCount =
                        countOccurrences(
                                mismatch,
                                pattern);

                int goodCount = 0;

                int last =
                        pattern.lastIndexOf(
                                mismatch);

                int badShift;

                if (last == -1)
                    badShift = j + 1;
                else
                    badShift = Math.max(
                            1,
                            j - last);

                System.out.println();

                System.out.println(
                        "Mismatch character = "
                                + mismatch);

                System.out.println(
                        "Bad Character Count = "
                                + badCount);

                System.out.println(
                        "Good Suffix Count = "
                                + goodCount);

                System.out.println(
                        "Shift = "
                                + badShift);

                shift += badShift;
            }
        }
    }

    public static void main(String[] args) {

        String text =
                "Insertion sort typically has a smaller constant factor than merge sort";

        String pattern = "sort";

        search(text, pattern);
    }
}