import java.util.ArrayList;

public class IDLList<E> {
	//Properties of IDLList<E>:
	private ArrayList<Node<E>> indices;
	private Node<E> head;
	private Node<E> tail;
	private int size;
	
	//Inner class Node<E>
	private class Node<E> {
		//Properties of Node<E>:
		private E data;
		private Node<E> prev;
		private Node<E> next;
		
		//Constructor of Node<E>, given 'E' elem
		public Node(E elem) {
			this.data = elem;
			this.prev = null;
			this.next = null;
		}
		
		//Constructor of Node<E>, given 'E' elem, a link to the previous node, and
		//a link to the next node
		public Node(E elem, Node<E> prev, Node<E> next) {
			this.data = elem;
			this.prev = prev;
			this.next = next;
		}
	}
	
	//Constructor of IDLList
	public IDLList() {
		this.indices = new ArrayList<Node<E>>();
		this.size = 0;
		this.head = null;
		this.tail = null;
	}
	
	
	//Constructor of IDLList
	public IDLList(E[] newIndices) {

		this.indices = new ArrayList<Node<E>>();
        if(newIndices.length>0){
            for(int i = 0; i<newIndices.length; i++){
                this.indices.add(new Node<E>(newIndices[i]));  
            }
            this.size = this.indices.size();
		    this.head = this.indices.get(0);
		    this.tail = this.indices.get(this.size - 1);

            for(int i = 0; i<newIndices.length;i++){
                if(i == 0){
                    this.indices.get(i).prev = null;
                    this.indices.get(i).next = this.indices.get(i+1);
                }
                else if(i == newIndices.length-1){
                    this.indices.get(i).prev = this.indices.get(i-1);
                    this.indices.get(i).next = null;
                }
                else{
                    this.indices.get(i).prev = this.indices.get(i-1);
                    this.indices.get(i).next = this.indices.get(i+1);
                }
            }
        }
        else{
            this.size = 0;
		    this.head = null;
		    this.tail = null;
        }
	}
	
	
	//Given an index, the .add() method adds a new node at the given index 
	public boolean add(int index, E elem) {
		Node node = new Node(elem);
		//Empty list
		if(this.size == 0){
			this.head = node;
			this.tail = node;
			this.indices.add(node);
		} 
		//OOB
		else if(index > this.size || index <0){
			throw new IllegalStateException("index must be within list size.");
		}
		//First element
		else if(index == 0){
			this.head.prev = node;
			node.next = this.head;
			this.head = node;
		} 
		//Last element
		else if (index == this.size){
			this.tail.next = node;
			node.prev = this.tail;
			this.tail = node;
		} 
		//In bounds
		else if (index < this.size){
			Node pointer = this.indices.get(index);
			node.prev = pointer.prev;
			node.next = pointer;
			pointer.prev.next = node;
			pointer.prev = node;
		} 
		this.indices.add(index, node);
		this.size++;
		return true;
	}
	
	//The .add() method adds a new node at the head
	public boolean add(E elem) {
		Node node = new Node(elem);
		if(this.size == 0){
			this.head = node;
			this.tail = node;
			this.size++;
			this.indices.add(0, node);
			return true;
		}
		this.head.prev = node;
		node.next = this.head;
		this.head = node;
		this.indices.add(0, node);
		this.size++;
		return true;
	}
	
	//The .append() method adds a new node at the tail
	public boolean append(E elem) {
		Node node = new Node(elem);
		if(this.size == 0){
			this.head = node;
			this.tail = node;
			this.size++;
			this.indices.add(node);
			return true;
		}
		this.tail.next = node;
		node.prev = this.tail;
		this.indices.add(node);
		this.size++;
		return true;
	}

	//The .get() method returns the data of a node at the given index
	public E get(int index) {
		try{
			return this.indices.get(index).data;
		} catch (Exception e){
			throw new IllegalStateException("Index OOB.");
		}
	}
	
	//The .getHead() method returns the data of the node at the head
	public E getHead() {
		if(this.size == 0){return null;}
        return this.head.data;
	}
		
	//The .getLast() method returns the data of the node at the tail
	public E getLast() {
		if(this.size == 0){return null;}
        return this.tail.data;
	}

	//The .remove() method removes the node at the head and returns the node's data
	public E remove() {
		if(this.size == 0){
			throw new IllegalStateException("List is empty.");
		}
		Node<E> out = this.head;
		this.head.next.prev = null;
		this.head = this.head.next;
		this.indices.remove(0);
		this.size--;
        return out.data;
	}	
		
	//The .removeLast() method removes the node at the tail and returns the node's data
	public E removeLast() {
		if(this.size == 0){
			throw new IllegalStateException("List is empty.");
		}
		Node<E> out = this.tail;
        this.tail.prev.next = null;
		this.tail = this.tail.prev;
		this.indices.remove(this.size-1);
		this.size--;
        return out.data;
	}
		
	//Given an index, the .removeAt() method removes the node at the index and returns its data
	public E removeAt(int index) {
		if(index >= this.size || index < 0){
			throw new IllegalStateException("Index OOB.");
		}
		Node<E> pointer = this.indices.get(index);
		try{
			pointer.next.prev = pointer.prev;
			pointer.prev.next = pointer.next;
		} catch (NullPointerException e){
			if(this.size == 0){
				this.head = null;
				this.tail = null;
			}
		}
		this.indices.remove(index);
		this.size--;
		return pointer.data;
	}
	
	//Given an element to remove, the .remove() method removes the node that is the first instance
	//in which the data is found to be the same
	public boolean remove(E elem) {
		Node pointer = this.head;
		if(this.head == null){return false;}
		while(pointer.next != null){
			if(pointer.data == elem){
				try{
					pointer.next.prev = pointer.prev;
					pointer.prev.next = pointer.next;
				} catch (NullPointerException e){
					if(this.size == 0){
						this.head = null;
						this.tail = null;
					}
				}
				this.indices.remove(pointer);
				this.size--;
				return true;
			}
			pointer = pointer.next;
		}
		return false;
	}

	//The .size() method returns the size of the ArrayList
	public int size() {
		return this.size;
	}

	//Converts the ArrayList into a readible string of each Node's data
	public String toString() {
		StringBuilder result = new StringBuilder("[");
		if (this.size > 0) {
			for (int i = 0; i < this.size; i++) {
				result.append(this.indices.get(i).data + ", ");
			}
			result.delete(result.length() - 2, result.length());
		}
		result.append("]");
		return result.toString();
	}

	public static void main(String[] args) {
		String al10[] = new String[7];
		al10[0] = "A";
        al10[1] = "B";
        al10[2] = "C";
        al10[3] = "D";
        al10[4] = "E";
        al10[5] = "F";
        al10[6] = "G";
		IDLList<String> L102 = new IDLList<String>(al10);
		System.out.println(L102.remove("D"));
		System.out.println(L102.remove("H"));
		System.out.println(L102.toString());
		
	}
}