package tiktokers;

import org.junit.*;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class ArrayListMyTikTokManagerTest {

    private ArrayListMyTikTokManager mgr;
    private ArrayListMyTikTokManager topTen;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        System.out.println(System.getProperty("user.dir"));
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    // NB: method name can be anything, as long as the annotation @Before exists
//    public void setUp() throws Exception {
    public void initialize() throws Exception {
        mgr = new ArrayListMyTikTokManager();
        topTen = new ArrayListMyTikTokManager();
        topTen.load("Top10TikTokers.txt");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void anEmptyDirectoryHasNoRumpledEater() {
        assertNull(mgr.get("@rumpledeater", "Followers"));
    }

    @Test
    public void basicRemove() {
        mgr.put("@user", "ExampleKey", "This is an example value.");
        mgr.remove("@user", "ExampleKey");

        assertNull(mgr.get("@user", "ExampleKey"));
    }

    @Test(expected = NoSuchElementException.class)
    public void removeNonExistentEntryEmptyManager() {
//        assertNull(mgr.remove("@nonexistent", "NoSuchKey"));
        mgr.remove("@nonexistent", "NoSuchKey");
    }

    @Test(expected = NoSuchElementException.class)
    public void removeNonExistentEntryNonEmptyManager() {
        mgr.put("@example1", "Key", "value");
        mgr.put("@example2", "Key", "value");

//        assertNull(mgr.remove("@nonexistent", "NoSuchKey"));
        mgr.remove("@nonexistent", "NoSuchKey");
    }

    @Test
    public void topTenContainsBurak() {
        assertNotNull(topTen.get("@cznburak", "Followers"));
    }
}