package recursion;

public class FactorialGenerator {
    public double nthFactorial(int n) {
        assert n >= 0 : "n must be non-negative";
        return computeFactorialRecurse(n);
    }

    // NOTE: for n >= 21, the result is negative because long overflowed
    private double computeFactorialRecurse(int n) {
        // 0! == 1! == 1
        if (n <= 1) return 1;
        else return computeFactorialRecurse(n - 1) * n;
    }

    public static void main(String[] args) {
        var inst = new FactorialGenerator();
        runAndPrint(inst, 0);
        runAndPrint(inst, 1);
        runAndPrint(inst, 6);
        runAndPrint(inst, 10);
        runAndPrint(inst, 20);

        System.out.println("==== Testing range [1,32] ====");
        for (int i = 1; i <= 32; i++) {
            runAndPrint(inst, i);
        }
        System.out.println("==============================");

        runAndPrint(inst, -1);
    }

    private static void runAndPrint(FactorialGenerator inst, int n) {
        System.out.println("nthFactorial(" + n + ") = " + inst.nthFactorial(n));
    }
}
