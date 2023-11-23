package mt2;

class LinkedListNode<T> {
	private LinkedListNode<T> next;
	private T content;

	public LinkedListNode(T content) {
		this.content = content;
	}

	public LinkedListNode<T> getNext() {
		return next;
	}

	public T getData() {
		return content;
	}

	public void setNext(LinkedListNode<T> next) {
		this.next = next;
	}

}

public class GenericLinkedList<T> {
	private static String a = "a";
	private static String b = "b";
	private static String c = "c";
	private static String d = "d";
	private LinkedListNode<T> head;
	private LinkedListNode<T> tail;

	public boolean checkLoop() {
		// check if there exists a loop

		// xxx fill in unfinished codes here

		return true;
	}

	public boolean checkHeadTail() {
		// check if the head and tail are valid

		// xxx fill in unfinished codes here

		return true;
	}

	public void replicateHead() {
		// xxx fill in unfinished codes

	}

	public void replicateTail() {
		// xxx fill in unfinished codes
	}

	public LinkedListNode<T> find(T findThis1, T findThis2, T findThis3) {
		// xxx fill in unfinished codes here.
		return null;
	}

	public void addToHead(T t) {
		LinkedListNode<T> node = new LinkedListNode<>(t);
		node.setNext(head);
		head = node;
		if (tail == null)
			tail = node;
	}

	public String toString() {

		String str = "";
		LinkedListNode<T> node = head;

		while (node != null) {
			str += node.getData();
			node = node.getNext();
			if (node != null) {
				str += "->";
			}
		}
		return str;
	}

	public static void testing(GenericLinkedList<String> ll) {

		boolean ok = ll.checkHeadTail();
		if (!ok) {
			String s = "The linked list is INVALID!!!";
			System.out.println(s);
			return;
		}
		ok = ll.checkLoop();
		if (!ok) {
			String s = "The linked list has a loop!!!";
			System.out.println(s);
			System.out.println();
			return;
		}

		System.out.println("\ntesting toString():");
		System.out.println(ll + "\n");

		for (int i = 0; i < 2; ++i) {
			System.out.println("testing replicateHead() and replicateTail()");
			ll.replicateHead();
			ll.replicateTail();
			System.out.println(ll);
		}
		System.out.println();

		System.out.println("testing find(d,b,a,):");
		System.out.println(ll + "\n");

		String[][] arr = { { d, b, a }, { b, a, a }, { a, a, a }, { a, b, c }, };
		for (String[] item : arr) {
			String x = item[0];
			String y = item[1];
			String z = item[2];
			LinkedListNode<String> node = ll.find(x, y, z);
			String ans = "null";
			if (node != null) {
				ans = node.getData();
			}
			System.out.format("find(%s,%s,%s) =  %s\n", x, y, z, ans);
		}
		System.out.println("-----------------------------------\n");
	}

	public static void main(String[] args) {

		GenericLinkedList<String> ll = new GenericLinkedList<>();

		testing(ll);

		ll.addToHead(a);
		ll.addToHead(b);
		testing(ll);

		ll.addToHead(d);
		ll.addToHead(c);
		testing(ll);

		System.out.println("Create an invalid linked list with a loop.\n");
		ll.head.setNext(ll.head);
		testing(ll);

		System.out.println("Create a linked list with invalid head .\n");
		ll.head = null;
		testing(ll);

	}
}
