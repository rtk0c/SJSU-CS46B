package tiktoks;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class SearcherTest {

    static final int FUZZ_TEST_SIZE = 500000;

    static TikToker[] even = new TikToker[10];
    static TikToker[] odd = new TikToker[9];
    static TikToker[] empty = new TikToker[0];
    static TikToker notFound;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        even[0] = odd[0] = new TikToker("@spencerx", 54900000);
        even[1] = odd[1] = new TikToker("@cznburak", 56600000);
        even[2] = odd[2] = new TikToker("@dixiedamelio", 57100000);
        even[3] = odd[3] = new TikToker("@kimberly.loaiza", 59900000);
        even[4] = odd[4] = new TikToker("@willsmith", 66300000);
        even[5] = odd[5] = new TikToker("@zachking", 67400000);
        even[6] = odd[6] = new TikToker("@addisonre", 86700000);
        even[7] = odd[7] = new TikToker("@bellapoarch", 88100000);
        even[8] = odd[8] = new TikToker("@charlidamelio", 137000000);
        even[9] = new TikToker("@khaby.lame", 137000000);

        notFound = new TikToker("@rumpledeater", 18);

    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void iterativeFoundOdd() {
        for (int idx = 0; idx < odd.length; idx++) {
            assertEquals(idx, Searcher.iterativeBinarySearch(odd[idx], odd));
        }
    }

    @Test
    public void iterativeFoundEven() {
        for (int idx = 0; idx < even.length; idx++) {
            assertEquals(idx, Searcher.iterativeBinarySearch(even[idx], even));
        }
    }

    @Test
    public void iterativeNotFoundOdd() {
        assertEquals(-1, Searcher.iterativeBinarySearch(notFound, odd));
    }

    @Test
    public void iterativeNotFoundEven() {
        assertEquals(-1, Searcher.iterativeBinarySearch(notFound, even));
    }

    @Test
    public void iterativeNotFoundEmpty() {
        assertEquals(-1, Searcher.iterativeBinarySearch(notFound, empty));
    }

    @Test
    public void iterativeFuzz() {
        var rd = new Random();
        var a = new TikToker[FUZZ_TEST_SIZE];
        var existing = new HashSet<Integer>();
        for (int i = 0; i < FUZZ_TEST_SIZE; i++) {
            int n;
            do {
                n = rd.nextInt();
            } while (existing.contains(n));
            existing.add(n);
            a[i] = new TikToker("Alice", n);
        }

        Arrays.sort(a);

        for (int i = 0; i < FUZZ_TEST_SIZE; i++) {
            assertEquals(i, Searcher.iterativeBinarySearch(a[i], a));
        }
    }


    @Test
    public void recursiveFoundOdd() {
        for (int idx = 0; idx < odd.length; idx++) {
            assertEquals(idx, Searcher.recursiveBinarySearch(odd[idx], odd));
        }
    }

    @Test
    public void recursiveFoundEven() {
        for (int idx = 0; idx < even.length; idx++) {
            assertEquals(idx, Searcher.recursiveBinarySearch(even[idx], even));
        }
    }

    @Test
    public void recursiveNotFoundOdd() {
        assertEquals(-1, Searcher.recursiveBinarySearch(notFound, odd));
    }

    @Test
    public void recursiveNotFoundEven() {
        assertEquals(-1, Searcher.recursiveBinarySearch(notFound, even));
    }

    @Test
    public void recursiveNotFoundEmpty() {
        assertEquals(-1, Searcher.recursiveBinarySearch(notFound, empty));
    }

    @Test
    public void recursiveFuzz() {
        var rd = new Random();
        var a = new TikToker[FUZZ_TEST_SIZE];
        var existing = new HashSet<Integer>();
        for (int i = 0; i < FUZZ_TEST_SIZE; i++) {
            int n;
            do {
                n = rd.nextInt();
            } while (existing.contains(n));
            existing.add(n);
            a[i] = new TikToker("Alice", n);
        }

        Arrays.sort(a);

        for (int i = 0; i < FUZZ_TEST_SIZE; i++) {
            assertEquals(i, Searcher.recursiveBinarySearch(a[i], a));
        }
    }
}

