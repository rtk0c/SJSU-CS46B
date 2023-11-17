package playlist;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class LinkedPlayListTest {

    static Song s1;
    static Song s2;
    static Song s3;
    static Song s4;
    static Song s5;

    LinkedPlayList empty = new LinkedPlayList();
    LinkedPlayList one = new LinkedPlayList();
    LinkedPlayList songs = new LinkedPlayList();

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        s1 = new Song("Bad Habits", "Ed Sheeran");
        s2 = new Song("Tides", "Ed Sheeran");
        s3 = new Song("Overpass Graffiti", "Ed Sheeran");
        s4 = new Song("Heat Waves", "Glass Animals");
        s5 = new Song("Ophelia", "The Lumineers");
    }

    @Before
    public void setUp() throws Exception {
        one.insertAtHead(s1);
        songs.insertAtHead(s4);
        songs.insertAtHead(s3);
        songs.insertAtHead(s2);
        songs.insertAtHead(s1);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void containsTrue() {
        assertTrue(songs.contains(s1));
        assertTrue(songs.contains(s2));
        assertTrue(songs.contains(s3));
        assertTrue(songs.contains(s4));
    }

    @Test
    public void containsFalse() {
        assertFalse(songs.contains(s5));
    }

    @Test
    public void append() {
        LinkedPlayList added = new LinkedPlayList();
        added.insertAtHead(s5);
        added.insertAtHead(s4);
        added.insertAtHead(s3);
        added.insertAtHead(s2);
        added.insertAtHead(s1);
        songs.append(s5);
        assertTrue(songs.equals(added));
    }

    @Test
    public void appendEmptyList() {
        var added = new LinkedPlayList();
        added.append(s1);
        added.append(s2);
        added.append(s3);
        added.append(s4);
        added.append(s5);
        songs.append(s5);
        assertEquals(added, songs);
    }

    @Test
    public void getHeadTest() {
        assertEquals(s1, songs.get(0).getSong());
    }

    @Test
    public void getRandomTest() {
        // """
        // //produced by a fair dice roll
        // int gerRandomNumber() { return 4; }
        // """
        assertEquals(s2, songs.get(1).getSong());
    }

    @Test
    public void getLastTest() {
        assertEquals(s4, songs.get(3).getSong());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getBefore0() {
        songs.get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getAfterSize() {
        songs.get(4);
    }

    @Test
    public void addInMiddle() {
        LinkedPlayList added = new LinkedPlayList();
        added.insertAtHead(s4);
        added.insertAtHead(s3);
        added.insertAtHead(s5);
        added.insertAtHead(s2);
        added.insertAtHead(s1);

        songs.add(2, s5);

        assertEquals(added, songs);
    }

    @Test
    public void addAtHead() {
        LinkedPlayList added = new LinkedPlayList();
        added.insertAtHead(s4);
        added.insertAtHead(s3);
        added.insertAtHead(s2);
        added.insertAtHead(s1);
        added.insertAtHead(s5);

        songs.add(0, s5);

        assertEquals(added, songs);
    }

    @Test
    public void addToEmptyList() {
        var songs = new LinkedPlayList();
        songs.insertAtHead(s1);

        var added = new LinkedPlayList();
        added.add(0, s1);

        assertEquals(added, songs);
    }

    @Test
    public void removeHead() {
        LinkedPlayList added = new LinkedPlayList();
        added.insertAtHead(s4);
        added.insertAtHead(s3);
        added.insertAtHead(s2);

        assertTrue(songs.removeOne(s1));
        assertEquals(added, songs);
    }

    @Test
    public void removeMiddle() {
        LinkedPlayList added = new LinkedPlayList();
        added.insertAtHead(s4);
        added.insertAtHead(s2);
        added.insertAtHead(s1);

        assertTrue(songs.removeOne(s3));

        assertEquals(added, songs);
    }

    @Test
    public void removeMultiple() {
        LinkedPlayList added = new LinkedPlayList();
        added.insertAtHead(s4);
        added.insertAtHead(s3);
        added.insertAtHead(s2);
        added.insertAtHead(s2);

        songs.insertAtHead(s1);
        songs.insertAtHead(s2);
        songs.insertAtHead(s1);

        assertTrue(songs.removeAll(s1));
        assertEquals(added, songs);
    }
}
