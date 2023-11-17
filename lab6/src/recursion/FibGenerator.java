package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FibGenerator {
    private static int[] knownFibonacciValues = new int[10];
    private int[] callCounter;

    static {
        // Fill everything with invalid markers
        Arrays.fill(knownFibonacciValues, -1);
    }

    private static int nextPowerOfTwo(int n) {
        // e.g. 0b00001011 -> 0b00001000
        int highBit = Integer.highestOneBit(n);
        return highBit << 1;
    }

    private static int[] resizeArray(int[] old, int minSize) {
        // Load factor = 2 because I'm too lazy to think about it more :)
        // I'm pretty sure ArrayList uses 2?
        int newLen = old.length;
        while (newLen < minSize) {
            newLen = nextPowerOfTwo(newLen);
        }
        return Arrays.copyOf(old, newLen);
    }

    // NOTE: integer overflow possible... lab does not ask to fix so it gets to stay! :)
    public int fib(int n) {
        assert n >= 1;

        // NOTE: we choose manual capacity handling to avoiding boxing when using ArrayList<Integer> which can be a major cache miss causer
        if (n > knownFibonacciValues.length) {
            int oldLen = knownFibonacciValues.length;
            knownFibonacciValues = resizeArray(knownFibonacciValues, n);
            int newLen = knownFibonacciValues.length;
            Logger.getGlobal().info("Resizing from " + oldLen + " to " + newLen);

            // Fill the new space with invalid markers
            for (int i = oldLen; i < newLen; i++) {
                knownFibonacciValues[i] = -1;
            }
        }

        callCounter = new int[n];

        return computeFibRecurse(n);
    }

    private int computeFibRecurse(int n) {
        Logger.getGlobal().info("Entering fib(" + n + ")");

        // Map n ∈ [1,∞) to [0,∈)
        int memoizationIdx = n - 1;

        callCounter[memoizationIdx]++;

        if (knownFibonacciValues[memoizationIdx] != -1) {
            return knownFibonacciValues[memoizationIdx];
        }

        if (n == 1 || n == 2) return 1;
        else {
            int res = computeFibRecurse(n - 1) + computeFibRecurse(n - 2);
            Logger.getGlobal().info("Exiting fib(" + n + ") = " + res);
            knownFibonacciValues[memoizationIdx] = res;
            return res;
        }
    }

    public void printCallCounter() {
        for (int i = 0; i < callCounter.length; i++) {
            System.out.println(callCounter[i] + " calls to fib(" + i + ")");
        }
    }

    public static void main(String[] args) {
        Logger.getGlobal().setLevel(Level.OFF);

        System.out.println("STARTING");
        var inst = new FibGenerator();
        runAndPrint(inst, 1);
        runAndPrint(inst, 2);
        runAndPrint(inst, 5);
        runAndPrint(inst, 10);

        runAndPrint(inst, 20);
        inst.printCallCounter();

        System.out.println("==== Testing range [1,100] ====");
        for (int i = 1; i <= 100; i++) {
            runAndPrint(inst, i);
        }
        System.out.println("==============================");
    }

    private static void runAndPrint(FibGenerator inst, int n) {
        System.out.println("fib(" + n + ") = " + inst.fib(n));
    }
}
