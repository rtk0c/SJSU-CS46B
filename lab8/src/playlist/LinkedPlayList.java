package playlist;

import java.util.Objects;

public class LinkedPlayList {

    static class Node {
        private Song song;
        private Node next;

        public Node(Song song, Node next) {
            this.song = song;
            this.next = next;
        }

        public Song getSong() {
            return song;
        }

        public Node getNode() {
            return next;
        }

    }

    private Node head;

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        int size = 0;
        Node current = head;
        while (current != null) {
            size++;
            current = current.next;
        }
        return size;
    }

    public void insertAtHead(Song song) {
        Node node = new Node(song, null);//new head points to old head
        node.next = head;
        head = node;
    }

    public boolean equals(Object o) {
        LinkedPlayList pl = (LinkedPlayList) o;
        if (pl.size() != size()) {
            return false;
        }
        Node current1 = pl.head;
        Node current2 = head;
        while (current1 != null) {
            if (!current1.song.equals(current2.song)) return false;
            current1 = current1.next;
            current2 = current2.next;
        }
        return true;
    }

    public int hashCode() {
        int hc = 0;
        Node current = head;
        while (current != null) {
            hc = hc + (current.getSong()).hashCode();
        }
        return hc;
    }

    public String toString() {
        String s = "";
        Node current = head;
        while (current != null) {
            s = s + current.song;
            current = current.next;
            s = s + "->";
        }
        return s;
    }

    //****IMPLEMENT THIS*******//
    //Returns true if the LinkedPlayList contains a Node with Song s
    //Returns false otherwise
    public boolean contains(Song s) {
        Node n = this.head;
        while (n != null) {
            // N.B. handles null == null transparently
            if (Objects.equals(s, n.getSong())) {
                return true;
            }
            n = n.getNode();
        }
        return false;
    }

    //****IMPLEMENT THIS*******//
    //Note that there is no tail instance variable
    //You should add a new Node containing Song, song
    //to the end of the PlayList
    public void append(Song song) {
        if (head == null) {
            head = new Node(song, null);
            return;
        }

        Node n = head;
        while (n.next != null) {
            n = n.next;
        }
        n.next = new Node(song, null);
    }

    //****IMPLEMENT THIS*******//
    //Returns that Node at the specified index in the List.
    //Remember the first element in the list has an index of 0
    //Throws an indexOutOfBoundsException if the index is less than 0
    // or greater than or equal to the size of the list
    public Node get(int index) {
        if (index < 0)
            throw new IndexOutOfBoundsException("LinkedPlayList.get(): cannot get at negative index");

        int nToGo = index;
        Node n = head;
        while (true) {
            if (n == null)
                throw new IndexOutOfBoundsException("LinkedPlayList.get(): cannot get beyond list size");
            if (nToGo == 0)
                break;
            n = n.next;
            nToGo--;
        }

        return n;
    }

    //****IMPLEMENT THIS*******//
    //Adds a new Node containing the Song song to the LinkedPlayList
    //at the current location. If I want to add a Song at the index 1,
    //the node at index 0 should be updated to point at the new Node and
    //the remaining nodes should shift down one. This is very similar to
    //insertAt method we completed in class
    //If the index is invalid, throw an IndexOutOfBoundsException
    //Think about how to use the get method to implement the add method
    public void add(int loc, Song song) {
        if (loc < 0)
            throw new IndexOutOfBoundsException("LinkedPlayList.add(): cannot insert at negative index");

        if (loc == 0) {
            head = new Node(song, head);
            return;
        }

        Node curr = head;
        int i = 0;
        while (true) {
            if (curr == null) throw new IndexOutOfBoundsException();
            if (i == loc - 1) {
                curr.next = new Node(song, curr.next);
                break;
            }
            curr = curr.next;
            i++;
        }

//        int nToGo = loc;
//        Node /*nullable*/ targetPrev = null;
//        Node target = head;
//        while (true) {
//            if (nToGo == 0)
//                break;
//            if (target == null)
//                throw new IndexOutOfBoundsException("LinkedPlayList.add(): cannot insert beyond list size");
//            nToGo--;
//            targetPrev = target;
//            target = target.next;
//        }
//
//        var n = new Node(song, target);
//        if (targetPrev != null) {
//            targetPrev.next = n;
//        } else {
//            head = n;
//        }
    }

    //****IMPLEMENT THIS*******//
    //remove the first node that contains the specified song and return true
    //if the list is empty throw an illegal argument exception
    //if the list doesn't contain the song, return false
    public boolean removeOne(Song song) {
        if (isEmpty())
            throw new IllegalArgumentException("LinkedPlayList.removeOne(): list is empty");

        Node targetPrev = null;
        Node target = head;
        while (true) {
            if (target == null)
                return false;
            if (Objects.equals(song, target.getSong()))
                break;
            targetPrev = target;
            target = target.getNode();
        }

        if (targetPrev != null) {
            targetPrev.next = target.next;
        } else {
            head = target.next;
        }

        return true;
    }

    //****IMPLEMENT THIS*******//
    //remove the all the nodes that contain the specified song and return true;
    //if the list is empty throw an illegal argument exception
    //if the list doesn't contain the song,
    //then print a useful message to the console
    public boolean removeAll(Song song) {
        if (isEmpty())
            throw new IllegalArgumentException("LinkedPlayList.removeAll(): list is empty");

        // Being lazy here, instead of doing the optimal thing of reusing nodes, we just construct a new list out of place _cons_ style

        Node res = null;
        Node resTail = null;

        Node curr = head;
        while (curr != null) {
            if (!Objects.equals(curr.getSong(), song)) {
                // O(1) append to a new list
                var newNode = new Node(curr.getSong(), null);
                if (res == null) {
                    res = resTail = newNode;
                } else {
                    resTail.next = newNode;
                    resTail = newNode;
                }
            }
            curr = curr.next;
        }

        this.head = res;

        // We have not reconstructed any element
        if (res == null) {
            System.out.println("LinkedPlayList.removeAll(): no element removed");
        }

        return true;
    }

    //****IMPLEMENT THIS*******//
    //Look for the node in the list that contains dataBefore
    //Create a new node that contains data and insert it immediately after the dataBefore node
    //For example if I call insertAfter(2,7) with the list [1,2,3]
    //the new list would be [1,2,7,3]
    //Returns true if the method successfully inserts the song and false otherwise
    public boolean insertAfter(Song prevSong, Song songToAdd) {
        Node curr = head;
        while (curr != null) {
            if (Objects.equals(curr.getSong(), prevSong)) {
                curr.next = new Node(songToAdd, curr.next);
                return true;
            }
            curr = curr.next;
        }

        return false;
    }
}
