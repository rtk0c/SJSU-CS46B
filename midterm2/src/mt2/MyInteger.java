package mt2;

import java.util.*;

class MyInteger implements Iterable<Integer> {
	private TreeSet<Integer> data;

	MyInteger(int[] inputs) {
		data = new TreeSet<Integer>();
		for (int i : inputs) {
			data.add(i);
		}
	}

	public ArrayList<Integer> getSorted() {
		// xxx fill in the missing codes

	}

	public Iterator<Integer> iterator() {
		// xxx fill in the missing codes
	}

	// Print out the astronauts in any order. You may not change this method.
	public void printAll() {
		System.out.print("My numbers: ");
		for (Integer a : this) {
			System.out.print(a + ", ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[][] arr = { { -1, -2, -3, -4, -5, 5, 4, 3, 2, 1, 0 }, { 5, 2, 3, 1, 0, -1, -2, -3, 4, -4, -5, },
				{ 0, 1, -1, 2, -2, 3, -3, 4, -4, 5, -5 } };

		for (int i = 0; i < arr.length; ++i) {
			MyInteger nn = new MyInteger(arr[i]);
			nn.printAll();
		}

	}
}
