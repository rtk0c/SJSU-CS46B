package bubble;

// ==== Pseudocode for Part 1 ====
// function bubbleSort(array):
//   // unit or empty arrays are sorted by default
//   if array.length <= 1
//     return
//   loop
//     let madeChanges = false
//     for i between 0 to array.length - 1
//       if array[i] > array[i + 1] // [the branch]
//         swap elements are (i) and (i + 1)
//         madeChanges = true
//     if not madeChanges
//       //everything is in the correct place, because not taking [the branch] means every element is in increasing order
//       break

// ==== Initial Big-O notation hypothesis ====
// bubble sort has O(n^2)
// because there are two loops, both of which traverses the input array twice (does work proportional to input length)

public class BubbleSorter {
    private int[] a;
    private long nVisits;
    private long nSwaps;


    public BubbleSorter(int[] a) {
        this.a = a;
    }


    public void sort() {
        nVisits = 0;
        nSwaps = 0;

        if (a.length <= 1)
            return;

        while (true) {
            boolean madeChanges = false;
            for (int i = 0; i < a.length - 1; i++) {
                int x = a[i];
                int y = a[i + 1];
                nVisits += 2;
                if (x > y) {
                    a[i] = y;
                    a[i + 1] = x;
                    nSwaps += 1;
                    madeChanges = true;
                }
            }

            if (!madeChanges)
                break;
        }
    }


    public String toString() {
        String s = nVisits + " visits, " + nSwaps + " swaps\n{";
        for (int n : a)
            s += " " + n;
        s += " }";
        return s;
    }

    public long getNVisits() {
        return nVisits;
    }


    public long getNSwaps() {
        return nSwaps;
    }


    public int[] getArray() {
        return a;
    }


}
