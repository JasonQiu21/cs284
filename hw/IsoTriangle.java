import java.util.function.LongToDoubleFunction;


class Pair<E>{
	E value1;
	E value2;
	
	protected Pair(E value1, E value2) {
		this.value1 = value1;
		this.value2 = value2;
	}
}
class Node<E> {
	E data;
	private Node<E> left, right;
	Integer depth;
	public Integer left_visit_count = 0;
	public Integer right_visit_count = 0;

	public Node(E data) {
		this.data = data;
		this.left = null;
		this.right = null;
		this.depth = null;
	}

	public Node(E data, Node<E> left, Node<E> right, int depth) {
		this.data = data;
		this.left = left;
		this.right = right;
		this.depth = depth;
	}

	public Node<E> getLeft(){
		this.left_visit_count += 1;
		return this.left;
	}

	public Node<E> getRight(){
		this.right_visit_count += 1;
		return this.right;
	}

	public String toString(){
		return this.data.toString();
	}
}
public class IsoTriangle {
	Integer total_iso_triangle = 0;
	/*
	1. Parent has to pass number of left/right steps down the tree from root
	2. Child has to pass number of left/right steps down to tree to leaves
	3. Terminal case: node has 0 left/right below
	4. Update solution by taking max of left steps above and right steps below and vice versa
	*/
	public Pair<Integer> helper(Node root, int upLeftCount, int upRightCount){
		int leftCount;
		int rightCount;
		if(root == null){
			if(upLeftCount == 0){rightCount = -1;} else {rightCount = 0;}
			if(upRightCount == 0){leftCount = -1;} else {leftCount = 0;}
		} else {
			leftCount = helper(root.getLeft(), upLeftCount + 1, 0).value1 + 1;
			rightCount = helper(root.getRight(), 0, upRightCount + 1).value2 + 1;
		}
		total_iso_triangle += Math.max(Math.min(upRightCount, leftCount), 0);
		total_iso_triangle += Math.max(Math.min(upLeftCount, rightCount), 0);
		Pair<Integer> out = new Pair(leftCount, rightCount);
		return out;	
	}


	public Pair<Integer> count_type2_iso_triangle(Node root) {
		return helper(root, 0, 0);
	}

/*
 * Test Method
 * Building a Tree
 * */
public Node<Integer> buildTree1_count_iso_triangle2() {
	Node<Integer> six = new Node<Integer>(6);
	Node<Integer> five = new Node<Integer>(5);
	Node<Integer> four = new Node<Integer>(4);
	Node<Integer> three = new Node<Integer>(3);
	Node<Integer> two = new Node<Integer>(2, five, six, 2);
	Node<Integer> one = new Node<Integer>(1, three, four, 2);
	Node<Integer> root= new Node<Integer>(0, one, two, 1);
return root;
}
public static void main(String[] args) {
		//Example
		IsoTriangle test = new IsoTriangle();
		Node<Integer> r = test.buildTree1_count_iso_triangle2();
		test.count_type2_iso_triangle(r);
		System.out.println("Total number of Type-2 and Type-3 iso triangles are:" + test.total_iso_triangle);
	}
}