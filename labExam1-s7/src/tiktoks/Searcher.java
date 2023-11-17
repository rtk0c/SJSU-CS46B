package tiktoks;

public class Searcher {

    public static int iterativeBinarySearch(TikToker t, TikToker[] a) {
        if (a.length == 0)
            return -1;

        // N.B. we look at [begin,end] -- against the convention of open-close ranges for convenience of midpoint calculation
        int begin = 0;
        int end = a.length - 1;
        while (begin <= end) {
            int mid = begin + (end - begin) / 2;
            int compareRes = a[mid].compareTo(t);
            if (compareRes < 0) {
                begin = mid + 1;
                continue;
            }
            if (compareRes > 0) {
                end = mid - 1;
                continue;
            }
            // compareRes == 0
            return mid;
        }
        return -1;
    }

    public static int recursiveBinarySearch(TikToker t, TikToker[] a) {
        if (a.length == 0)
            return -1;
        return recHelper(t, a, 0, a.length - 1);
    }

    // Binary serach in range [begin,end]
    private static int recHelper(TikToker t, TikToker[] a, int begin, int end) {
        if (begin > end)
            return -1;

        int mid = begin + (end - begin) / 2;
        int compareRes = a[mid].compareTo(t);
        if (compareRes < 0) {
            return recHelper(t, a, mid + 1, end);
        }
        if (compareRes > 0) {
            return recHelper(t, a, begin, mid - 1);
        }
        // compareRes == 0
        return mid;
    }
}
