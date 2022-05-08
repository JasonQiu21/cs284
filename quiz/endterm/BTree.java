import java.nio.channels.NetworkChannel;

class Node<E> {
		
	Integer value;
	Node<E> left;
	Node<E> right;
	
	public Node(Integer value, Node<E> left, Node<E> right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}

	public String toString() {
		return "value=" + value.toString();
	}
}

public class BTree<E> {
	
	Node<E> root;
	
	
	/**
	* @return a new binary tree resulting from adding the tree recipient 
	/* of the message (i.e. the one referred to by this) and t2.
	*/
	public BTree<E> sumTree(BTree<E> t2){
		BTree<E> tree, lt, rt, lt2, rt2;
		int sum;
		tree = new BTree<E>();

		//If both are null, return null tree
		if(this.root == null && t2.root == null){
			tree.root = null;
			return tree;
		}
		//If one is null, the other's branches from that root are added to the tree
		if(this.root == null){
			sum = t2.root.value;
			tree.root = new Node<E>(sum, t2.root.left, t2.root.right);
			return tree;
		}
		if(t2.root == null){
			sum = root.value;
			tree.root = new Node<E>(sum, root.left, root.right);
			return tree;
		}
		
		//If neither is null, root node is summed, tree is built recursively from there
		sum = this.root.value + t2.root.value;

		//Left trees starting from root and t2
		lt = new BTree<E>();
		lt.root = root.left;
		lt2 = new BTree<E>();
		lt2.root = t2.root.left;
		
		//Right trees starting from root and t2
		rt = new BTree<E>();
		rt.root = root.right;
		rt2 = new BTree<E>();
		rt2.root = t2.root.right;

		//Recursive call
		Node<E> node = new Node(sum, lt.sumTree(lt2).root, rt.sumTree(rt2).root);
		tree.root = node;
		return tree;
	}

	public String toString() {
		// string representation of the btree
		StringBuilder sb = new StringBuilder();
		preOrderTraverse(this.root, 1, sb);
		return sb.toString();
	}

	private void preOrderTraverse(Node<E> node, int depth, StringBuilder sb) {
		// helper to toString, traverses btree and adds to stringbuilder
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
		BTree<Integer> test = new BTree<Integer>();
		Node five = new Node(5, null, null);
		Node three = new Node(3, five, null);
		Node two = new Node(2, null, null);
		Node one = new Node(1, three, two);
		test.root = one;

		BTree<Integer> t2 = new BTree<Integer>();
		Node four = new Node(4, null, null);
		Node one_ = new Node(1, null, four);
		Node seven = new Node(7, null, null);
		Node three_ = new Node(3, null, seven);
		Node two_ = new Node(2, one_, three_);
		t2.root = two_;

		BTree<Integer> out = test.sumTree(t2);

		System.out.println(out.toString());
	}
}
