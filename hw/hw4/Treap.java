import java.util.Random;
import java.util.Stack;

public class Treap<E extends Comparable<E>> {

	private static class Node<E extends Comparable<E>> {
		E data;
		int priority;
		Node<E> left;
		Node<E> right;

		public Node(E data, int priority) {
			if (data == null)
				throw new IllegalArgumentException();
			this.data = data;
			this.priority = priority;
			this.left = null;
			this.right = null;

		}

		Node<E> rotateRight() {
			if (this.left == null)
				return this;
			Node<E> b = this.left;
			this.left = b.right;
			b.right = this;
			return b;
		}

		Node<E> rotateLeft() {
			if (this.right == null)
				return this;
			Node<E> b = this.right;
			this.right = b.left;
			b.left = this;
			return b;
		}

		public String toString() {
			return "(key=" + data.toString() + ", priority=" + priority + ")";
		}
	}

	private Random priorityGenerator;
	private Node<E> root;

	public Treap() {
		this.root = null;
		this.priorityGenerator = new Random();
	}

	public boolean add(E key) {
		int p = this.priorityGenerator.nextInt();
		return add(key, p);
	}

	public boolean add(E key, int priority) {
		if(this.root == null){
			this.root = new Node(key, priority);
			return true;
		}
		Boolean notAdded = true;
		Node node = root;
		Stack stack = new Stack();
		while(notAdded){
			stack.add(node);
			if(node.data.compareTo(key) > 0){ // key < node
				if(node.left == null){
					node.left = new Node(key, priority);
					notAdded = false;
				}
				node = node.left;
			} else if(node.data.compareTo(key) < 0){
				if(node.right == null){
					node.right = new Node(key, priority);
					notAdded = false;
				}
				node = node.right;
			} else {
				return false;
			}
		}
		this.reheap(stack, node);
		return true;
	}
	private void reheap(Stack<Node<E>> s, Node<E> curr) {
		// helper to add, restores invariant
		Node parent = s.pop();
		Node prev = null;
		while(curr.priority > parent.priority){
			if(parent.left == curr){
				parent.rotateRight();
			} else if(parent.right == curr){
				parent.rotateLeft();
			}
			if(parent == this.root){this.root = curr;}
			if(s.isEmpty()){return;}
			prev = parent;
			parent = s.pop();
			if(prev != null && parent.left == prev){
				parent.left = curr;
			} else if(prev != null){
				parent.right = curr;
			}
		}
	}

	public boolean delete(E key) {
		if(!this.find(key)){return false;}
		Node node = root;
		Stack<Node<E>> stack = new Stack();
		while(true){
			stack.add(node);
			if(node.data.compareTo(key) > 0){ // key < node
				node = node.left;
			} else if(node.data.compareTo(key) < 0){
				node = node.right;
			} else if(node.data.compareTo(key) == 0){break;}
		}
		node = stack.pop();
		Node parent = stack.peek();
		while(node.right != null && node.left != null){
			if(node.left.data.compareTo(node.right.data) > 0){ // left > right - rotate right
				if(parent.right == node){
					parent.right = node.left;
				} else {
					parent.left = node.left;
				}
				parent = node.left;
				node.rotateRight();
			} else {
				if(parent.right == node){
					parent.right = node.right;
				} else {
					parent.left = node.right;
				}
				parent = node.right;
				node.rotateLeft();
			}
		}
		if(node.right == null){
			if(parent.right == node){parent.right = node.left;} else {parent.left = node.left;}
		} else if(node.left == null){
			if(parent.right == node){parent.right = node.right;} else {parent.left = node.right;}
		}
		return true;
	}

	boolean find(E key) {
		// return if node with key key exists
		Boolean found = false;
		Node node = root;
		while(!found){
			if(node == null){break;}
			if(node.data.compareTo(key) > 0){ // key < node
				node = node.left;
			} else if(node.data.compareTo(key) < 0){
				node = node.right;
			} else if(node.data.compareTo(key) == 0){found = true;}
		}
		return found;
	}

	public String toString() {
		// string representation of the treap
		StringBuilder sb = new StringBuilder();
		preOrderTraverse(this.root, 1, sb);
		return sb.toString();
	}

	private void preOrderTraverse(Node<E> node, int depth, StringBuilder sb) {
		// helper to toString, traverses treap and adds to stringbuilder
		for (int i = 1; i < depth; i++) {
			sb.append("  ");
		}
		if (node == null) {
			sb.append("null\n");
		} else {
			sb.append(node.toString());
			sb.append("\n");
			preOrderTraverse(node.left, depth + 1, sb);
			preOrderTraverse(node.right, depth + 1, sb);
		}
	}

	public static void main(String[] args) {
		Treap<Integer> testTree = new Treap<Integer>();
		testTree.add(4, 19);
		testTree.add(2, 31);
		testTree.add(6, 70);
		testTree.add(1, 84);
		testTree.add(3, 12);
		testTree.add(5, 83);
		testTree.add(7, 26);

		testTree.delete(5);
		System.out.println(testTree.toString());
	}

}