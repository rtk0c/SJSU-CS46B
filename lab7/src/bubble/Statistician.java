package bubble;

import java.util.*;


public class Statistician {
    private final static int N_REPETITIONS = 1000;


    //returns a array of random integers with values between -maxValue and maxValue
    private static int[] buildRandom(int length, int maxValue) {
        int[] array = new int[length];
        for (int i = 0; i < length; i++)
            array[i] = (int) (Math.random() * (maxValue + 1));
        return array;
    }

    private static boolean isSorted(int[] a) {
        // ```diff
        // -for (int i = 0; i < a.length - 2; i++) {
        // +for (int i = 0; i < a.length - 1; i++) {
        // ```
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) {
                return false;
            }
        }
        return true;
    }

    private static long calcMin(List<Long> data) {
        long min = Long.MAX_VALUE;
        for (long n : data) {
            if (n < min)
                min = n;
        }
        return min;
    }

    private static long calcMax(List<Long> data) {
        long max = Long.MIN_VALUE;
        for (long n : data) {
            if (n > max)
                max = n;
        }
        return max;
    }

    private static double calcAverage(List<Long> data) {
        double sum = 0.0;
        for (long n : data) {
            sum += n;
        }
        double avg = sum / data.size();
        return avg;
    }

    private static void getStats(int arrayLength) {
        var visitCounts = new ArrayList<Long>();
        var swapCounts = new ArrayList<Long>();
        visitCounts.ensureCapacity(arrayLength);
        swapCounts.ensureCapacity(arrayLength);

        for (int i = 0; i < N_REPETITIONS; i++) {
            int[] array = buildRandom(arrayLength, arrayLength * 100);
            BubbleSorter sorter = new BubbleSorter(array);
            sorter.sort();
            // Assert that the sorter sorted correctly.
            // Append # visits and # swaps to the array lists.

            assert isSorted(sorter.getArray());

            visitCounts.add(sorter.getNVisits());
            swapCounts.add(sorter.getNSwaps());
        }

        // Compute and print min/average/max number of visits.
        // Compute and print min/average/max number of swaps.
        System.out.printf("Visits: min = %-10d  max = %-10d  avg = %-10f%n", calcMin(visitCounts), calcMax(visitCounts), calcAverage(visitCounts));
        System.out.printf("Swaps:  min = %-10d  max = %-10d  avg = %-10f%n", calcMin(swapCounts), calcMax(swapCounts), calcAverage(swapCounts));
    }

    // Test case      | Size | Number of visits | Number of swaps
    // ==========================================================
    // Tiny           | 4    | 12               | 1
    // Already sorted | 16   | 30               | 0
    // Backward       | 16   | 480              | 120

    public static void main(String[] args) {
        int[] tiny = {1, 24, 5, 25};
        int[] alreadySorted = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};//fill in your example
        int[] backward = {53, 49, 40, 35, 20, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};//fill in your example
        System.out.println("Tiny (size=" + tiny.length + ")");
        BubbleSorter tinySorter = new BubbleSorter(tiny);
        tinySorter.sort();
        System.out.println(tinySorter);

        System.out.println("Already Sorted (size=" + alreadySorted.length + ")");
        BubbleSorter alreadySortedSorter = new BubbleSorter(alreadySorted);
        alreadySortedSorter.sort();
        System.out.println(alreadySortedSorter);

        System.out.println("Backward (size=" + backward.length + ")");
        BubbleSorter backwardSorter = new BubbleSorter(backward);
        backwardSorter.sort();
        System.out.println(backwardSorter);

        // We expect statistics of the 3000s run to be about 9 times the 1000s run
        // that is, we consider the complexity to be O(n), so
        //     1000^2 => v
        //     3000^2 = (3*1000)^2 = 9*1000^2 => 9v

        /* One sample run output:
        1000:
        Visits: min = 1732266     max = 1996002     avg = 1921982.094000
        Swaps:  min = 233860      max = 267477      avg = 249611.741000
        3000:
        Visits: min = 16776406    max = 17976006    avg = 17592907.742000
        Swaps:  min = 2172790     max = 2337076     avg = 2250157.433000
         */
        // visits: 17592907 / 1921982 ≈ 9.153
        // swaps:  2250157 / 249611 ≈ 9.014
        // We can conclude that the data resonably confirms our hypothesis

        System.out.println("1000:");
        getStats(1000);

        System.out.println("3000:");
        getStats(3000);
    }
}
