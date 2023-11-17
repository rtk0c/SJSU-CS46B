package tiktoks;

public class Sorter {

    public static void insertionSortIterative(TikToker[] a) {
        if (a.length <= 1)
            return;

        // Sorted range: [0,srEnd) where srEnd <= a.length
        // For every element in the array, we insert it into the sorted range (growing its size by one)
        // That is, srEnd == i in the loop before

        for (int i = 0; i < a.length; i++) {
            // The element to be inserted into the sorted range
            // N.B. we need to copy it here because shiftRange() will overwrite a[i]
            var elm = a[i];

            for (int j = 0; j < i; j++) {
                if (a[j].compareTo(elm) > 0) {
                    shiftRange(a, j, i);
                    a[j] = elm;
                    break;
                }
            }
            // If none of the elements in the range are larger, we just place elm at srEnd, i.e. a no-op
        }
    }

    /**
     * Shift (copy) the elements in [begin,end) backwards by 1, leaving the elements at begin in place.
     */
    private static void shiftRange(TikToker[] a, int begin, int end) {
        assert begin < end;
        assert end + 1 <= a.length;

        for (int i = end - 1; i >= begin; i--) {
            a[i + 1] = a[i];
        }
    }

    public static void insertionSortRecursive(TikToker[] a) {
        insertRecurse(a, a.length);
    }

    // Sort the range [0,end) of a
    private static void insertRecurse(TikToker[] a, int end) {
        if (end <= 1)
            return;

        var theElm = a[end - 1];
        insertRecurse(a, end - 1);

        // Insert theElm at the correct location in [0,end)
        for (int i = 0; i < end; i++) {
            if (a[i].compareTo(theElm) > 0) {
                shiftRange(a, i, end - 1);
                a[i] = theElm;
                break;
            }
        }
        // theElm greater than all elements in range [0,end), so we can leave it in place
        // i.e. a no-op
    }
}
