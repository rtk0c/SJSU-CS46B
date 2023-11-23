package mt2;

public class Tree {
	class GenericNode {
		private int data;
		private GenericNode left;
		private GenericNode right;

		GenericNode(int data) {
			this.data = data;
		}

		void setLeft(GenericNode left) {
			this.left = left;
		}

		void setRight(GenericNode right) {
			this.right = right;
		}

		GenericNode getLeft() {
			return left;
		}

		GenericNode getRight() {
			return right;
		}

		public boolean isLeaf() {
			return left == null && right == null;
		}
	}

	private GenericNode root;

	public int countLeafNodes() {
		return countLeafNodesRecurse(root);
	}

	public int countLeafNodesRecurse(GenericNode node) {
		// xxx fill in the missing code
	}

	private void populateForTesting() {
		root = new GenericNode(0);
		GenericNode n1 = new GenericNode(1);
		GenericNode n2 = new GenericNode(2);
		GenericNode n3 = new GenericNode(3);
		GenericNode n4 = new GenericNode(4);
		GenericNode n5 = new GenericNode(5);
		GenericNode n6 = new GenericNode(6);

		root.setLeft(n1);
		root.setRight(n2);
		n2.setLeft(n3);
		n2.setRight(n4);

		n3.setLeft(n5);
		n3.setRight(n6);
		System.out.println(countLeafNodes());
	}

	private void populateForTesting2(int n) {
		root = new GenericNode(0);
		GenericNode curr = root;
		for (int i = 0; i < n; ++i) {
			GenericNode n1 = new GenericNode(2 * i);
			GenericNode n2 = new GenericNode(2 * i + 1);
			curr.setLeft(n1);
			curr.setRight(n2);
			curr = n1;
		}
		System.out.println(countLeafNodes());
	}

	public static void main(String[] args) {
		Tree tree = new Tree();
		tree.populateForTesting();
		for (int i = 0; i < 3; ++i) {
			tree.populateForTesting2(i);
		}
	}
}
