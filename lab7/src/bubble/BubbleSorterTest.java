package bubble;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class BubbleSorterTest {
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
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
    public void testTiny() {
        int[] a = {1, 24, 5, 10};
        int[] sorted = {1, 5, 10, 24};

        BubbleSorter sorter = new BubbleSorter(a);
        sorter.sort();
        //what line of code should I add here to see if the Array is Sorted?

        // N.B. this also works since BubbleSorter does not make a copy to `a`
        //assertArrayEquals(a, sorted);
        assertArrayEquals(sorter.getArray(), sorted);
    }

    @Test
    public void testBackward() {
        //This should test your algorithm on an array that is in reverse order

        int[] a = {53, 49, 40, 35, 20, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        int[] sorted = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 20, 35, 40, 49, 53};

        var sorter = new BubbleSorter(a);
        sorter.sort();

        assertArrayEquals(sorter.getArray(), sorted);
    }

    @Test
    public void testAlreadySorted() {
        //This should test your algorithm on a sorted array

        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        int[] sorted = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};

        var sorter = new BubbleSorter(a);
        sorter.sort();

        assertArrayEquals(sorter.getArray(), sorted);
    }
}
