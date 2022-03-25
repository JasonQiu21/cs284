import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Pair{
	Integer max_left_path_value;
	Integer max_right_path_value;

	protected Pair(Integer max_left_path_value, Integer max_right_path_value) {
		this.max_left_path_value = max_left_path_value;
		this.max_right_path_value = max_right_path_value;
	}

	@Override
	public String toString() {
		return "Pair{" +
				"max_left_path_value=" + max_left_path_value +
				", max_right_path_value=" + max_right_path_value +
				'}';
	}
}

class Node<E extends Comparable<E>> {

	E value;
	Node<E> l_child;
	Node<E> r_child;


	Node(E value, Node<E> l_child, Node<E> r_child) {
		this.value = value;
		this.l_child = l_child;
		this.r_child = r_child;
	}

	@Override
	public String toString() {
		return "Node{" +
				"value=" + value +
				'}';
	}
}



public class Triangle {


	private Integer maxSum = -1;

	public int getRightMax(Node<Integer> root, int max){
		if(root.value > max){max = root.value;}
		if(root.r_child == null){
			return max;
		} else {
			return getRightMax(root.r_child, max);
		}
	}
	public int getLeftMax(Node<Integer> root, int max){
		if(root.value > max){max = root.value;}
		if(root.l_child == null){
			return max;
		} else {
			return getLeftMax(root.l_child, max);
		}
	}

	public int triangle(Node<Integer> root){
		int leftMax, rightMax;
		if(root.l_child == null || root.r_child == null){return 0;}
		leftMax = getLeftMax(root.l_child, 0);
		rightMax = getRightMax(root.r_child, 0);
		int sum = root.value + leftMax + rightMax;
		return sum;
	}

	public Pair max_triangle(Node < Integer > root ) {
		if(root == null){return new Pair(0, 0);}
		int sum = triangle(root);
		System.out.println(sum);
		Pair left_triangle, right_triangle;
		left_triangle = max_triangle(root.l_child);
		right_triangle = max_triangle(root.r_child);
		this.maxSum = Math.max(this.maxSum, Math.max(sum, Math.max(Math.max(left_triangle.max_left_path_value, left_triangle.max_left_path_value), Math.max(right_triangle.max_right_path_value, right_triangle.max_left_path_value))));
		return new Pair(Math.max(left_triangle.max_left_path_value, left_triangle.max_left_path_value), Math.max(right_triangle.max_right_path_value, right_triangle.max_left_path_value));
	}


	public Integer getMaxSum() {
		return maxSum;
	}

	public Node<Integer> testcase1() {
		Node<Integer> six = new Node<Integer>(6, null, null);
		Node<Integer> five = new Node<Integer>(5, null, null);
		Node<Integer> four = new Node<Integer>(4, null, null);
		Node<Integer> three = new Node<Integer>(3, null, null);
		Node<Integer> two = new Node<Integer>(2, five, six);
		Node<Integer> one = new Node<Integer>(1, three, four);
		Node<Integer> root= new Node<Integer>(0, one, two);
		return root;
	}

	public static void main(String[] args) {
		//Example
		Triangle test = new Triangle();
		Node<Integer> r = test.testcase1();
		test.max_triangle(r);
		System.out.println("The max triangle sum is:" + test.getMaxSum());
	}
}

