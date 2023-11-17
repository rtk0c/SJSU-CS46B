package linked;


public class DNALinkedList {
    private static class Node<T> {
        private T data;
        private Node<T> prev;    // previous
        private Node<T> next;


        Node(T data) {
            this.data = data;
        }

        // Returns data of prev node, this node, and next node. Uses "<" if prev is
        // null, and ">" if next is null.
        public String toString() {
            String s;
            if (prev == null) s = "<";
            else s = prev.data.toString();

            s += data;

            if (next == null) s += ">";
            else s += next.data;

            return s;
        }

    }


    private Node<Character> head;    // head.prev is always null
    private Node<Character> tail;    // tail.next is always null


    public DNALinkedList(String s) {
        append(s);
    }


    // Used by extraction methods. Not for public use.
    private DNALinkedList(Node<Character> head, Node<Character> tail) {
        this.head = head;
        head.prev = null;
        this.tail = tail;
        tail.next = null;
    }


    // Converts arg to nodes which are appended to end of this list.
    public void append(String s) {
        for (int i = 0; i < s.length(); i++)
            append(s.charAt(i));
    }


    // Creates a node for ch and appends it to the linked list.
    // "Append" always means "at the end".
    public void append(char ch) {
        append(new Node<Character>(ch));
    }

    public String toString() {
        String s = "DNALinkedList: ";
        if (head == null) s += "Empty";
        else {
            Node<Character> n = head;
            while (n != null) {
                s += n.data;
                n = n.next;
            }
        }
        return s;
    }


    // Appends n to tail of this list.
    public void append(Node<Character> n) {
        // Corner case: empty list.
        if (tail == null) {
            n.prev = null;
            n.next = null;
            head = n;
            tail = n;
        } else {
            // General case.
            tail.next = n;
            n.prev = tail;
            tail = n;
        }
    }

    // Returns true if the nodes starting at startNode match the target string. For example,
    // if the linked list looks like this:
    // (A) —> (B) -> (C) -> (D) -> (E) -> (F)
    // and if startNode is the 2nd node (“B”), then matches(startNode, “BCDE”) should return true.
    private boolean matches(Node<Character> startNode, String target) {
        Node<Character> n = startNode;
        int i = 0;
        while (n != null && i < target.length()) {
            if (n.data != target.charAt(i)) {
                return false;
            }
            n = n.next;
            i++;
        }
        return true;
    }


    // If this list contains a chain of nodes whose data is the target, returns
    // the node at the start of that chain. If the target appears multiple times
    // in this list, returns the first occurrence. If the target is not in this list,
    // returns null.
    // Better name: findFirstSubsequence
    public Node<Character> find(String target) {
        Node<Character> n = head;
        // TODO implement skip-ahead
        while (n != null) {
            if (matches(n, target)) {
                return n;
            }
            n = n.next;
        }
        return null;
    }


    // Extract and return the nodes starting at firstExtractedNode and ending at lastExtractedNode.
    // The returned nodes should be a DNALinkedList. Don’t worry about any corner cases: assume
    // firstExtractedNode and lastExtractedNode are both in the list and are not near the head or tail.
    public DNALinkedList extract(Node<Character> firstExtractedNode, Node<Character> lastExtractedNode) {
        // Find nodes just before and just after the chain to be extracted. Assume these
        // aren't null.
        Node<Character> beforeFirst = firstExtractedNode.prev;
//        assert beforeFirst != null;
        Node<Character> afterLast = lastExtractedNode.next;
//        assert afterLast != null;

        // Connect beforeFirst to afterLast.
        if (beforeFirst != null)
            beforeFirst.next = afterLast;
        else
            head = afterLast;
        if (afterLast != null)
            afterLast.prev = beforeFirst;
        else
            tail = beforeFirst;

        // Return a DNALinkedList containing the extracted chain.
        return new DNALinkedList(firstExtractedNode, lastExtractedNode);
    }

    // Inserts insertMe into this list, at the node before insertionPoint. Assumes
    // insertionPoint is not the head or tail.
    public void insert(DNALinkedList insertMe, Node<Character> insertionPoint) {
        // Find node immediately before insertion point.
        Node<Character> beforeInsertionPoint = insertionPoint.prev;
//        assert beforeInsertionPoint != null;

        // Connect node immediately before insertion point to head of insertMe.
        if (beforeInsertionPoint != null)
            beforeInsertionPoint.next = insertMe.head;
        else
            head = insertMe.head;
        insertMe.head.prev = beforeInsertionPoint;

        // Connect tail of insertMe to insertionPoint node.
        insertionPoint.prev = insertMe.tail;
        insertMe.tail.next = insertionPoint;
    }


    // Reverses the order of the nodes.
    public void reverse() {
        // Swap next and prev of every node. Caution: in your loop, you won't be able
        // to advance n by setting n = n.next(). Why? How should you advance n?
        Node<Character> n = head;
        while (n != null) {
            var tmp = n.next;
            n.next = n.prev;
            n.prev = tmp;
            n = tmp;
        }

        // Swap head and tail.
        var tmp = head;
        head = tail;
        tail = tmp;
    }


    // Removes sequence matching transposon, reverses it, and inserts it back into
    // this list immediately before target. Throws IllegalArgumentException if
    // can't find transposon or target. Use the methods you just wrote.
    public void transpose(String transposon, String target) {
        // Find starting node of transposon.
        Node<Character> firstNodeOfTransposon = find(transposon);
        if (firstNodeOfTransposon == null)
            throw new IllegalArgumentException("Cannot find transposon");

        // Find last node of transposon. You'll need several lines. Set a variable to the first node
        // of the transposon, then do a loop where for every char in the transposon, you set the variable
        // to its "next".
        Node<Character> lastNodeOfTransposon = firstNodeOfTransposon;
        for (int i = 0; i < (target.length() - 1); i++) {
            lastNodeOfTransposon = lastNodeOfTransposon.next;
        }

        // Extract the transposon.
        DNALinkedList transposonList = extract(firstNodeOfTransposon, lastNodeOfTransposon);

        // Reverse the transposon.
        transposonList.reverse();

        // Find starting node of target.
        Node<Character> firstNodeOfTarget = find(target);
        if (firstNodeOfTarget == null)
            throw new IllegalArgumentException("Cannot find target sequence");

        // Insert immediately before target.
        insert(transposonList, firstNodeOfTarget);
    }


    public static void main(String[] args) {
        testTransposition("ATCAGGGGG", "TC", "GGG");
        testTransposition(trimSpaces("ATG GCA GTA GCA GCA TAG ATC GGA GAA TAC TGA"), "GCAGCA", "GAATAC");
        System.out.println("should be : " + new DNALinkedList(trimSpaces("ATG GCA GTA TAG ATC GGA ACG ACG GAA TAC TGA")));

        // Test edge cases for insert() and extract()
        {
            var ll = new DNALinkedList("ABCD");
            System.out.println(ll.extract(ll.head, ll.head.next));
        }
        {
            var ll = new DNALinkedList("ABCD");
            System.out.println(ll.extract(ll.tail.prev, ll.tail));
        }
        {
            var ll = new DNALinkedList("ABCD");
            ll.insert(new DNALinkedList("ZZ"), ll.head);
            System.out.println(ll);
        }
    }

    private static String trimSpaces(String s) {
        var sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != ' ') {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private static void testTransposition(String chromosome, String transposon, String target) {
        DNALinkedList list = new DNALinkedList(chromosome);
        System.out.println("original  : " + list);
        list.transpose(transposon, target);
        System.out.println("transposed: " + list);
    }

}
