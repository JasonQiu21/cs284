public class CompressingLinkedList {
	
	private static class CompressedNode {
		private Integer data;
		private Integer repetitions;
		private CompressedNode next;
		  
		/** Creates a new compressed node with a null next field
			@param dataItem  The data stored
		*/
		private CompressedNode(Integer dataItem) {
			data = dataItem;
		    next = null;
		}

		/**
		  * set the number of copies as copy
		  * @param copy
		  */
		private void set_repetitions(Integer rep) {
			repetitions = rep;
		}
	}

	CompressedNode head;
	
	/**
	 * compresses a list by skipping even numbers
	 * @param node_head
	 */
	public void compress(Node node_head){
		CompressedNode cnode;
		Node node = node_head;
		Node nodeNext = node_head.getNext();
		int curVal = node.getData();
		int count = 1;
		boolean initial = true;
		CompressedNode prev = null;
		while (nodeNext != null){
			if(nodeNext.getData() == curVal){
				count++;
			} else {
				cnode = new CompressedNode(curVal);
				cnode.set_repetitions(count);
				curVal = nodeNext.getData();
				if(initial) {
					initial = false;
					this.head = cnode;
				} else {
					prev.next = cnode;
				}
				prev = cnode;
				count = 1;
			}
			node = nodeNext;
			nodeNext = node.getNext();
		}
		cnode = new CompressedNode(curVal);
		cnode.set_repetitions(count);
		prev.next = cnode;
;
	}

	/**
	 * return the string of the linked list
	 */
	public String toString() {
	    StringBuilder sb = new StringBuilder("[");
	    CompressedNode cn = head;
	    if (cn != null) {
	        while (cn.next != null) {
	        	sb.append("(");
	            sb.append(cn.data.toString());
	            sb.append(",");
	            sb.append(cn.repetitions);
	            sb.append(")");
	            sb.append(",");
	            cn = cn.next;
	        }
	        sb.append("(");
	        sb.append(cn.data.toString());
	        sb.append(",");
            sb.append(cn.repetitions);
            sb.append(")");
	    }
	    sb.append("]");
	    return sb.toString();
	}

	public static void main(String[] args) {
		// // [4,4,4,1,1] -> [(4,3),(1,2)]
		// Node a = new Node(4);
		// Node b = new Node(4);
		// Node c = new Node(4);
		// Node d = new Node(1);
		// Node e = new Node(1);
		// a.setNext(b);
		// b.setNext(c);
		// c.setNext(d);
		// d.setNext(e);

		// [3,3,2,3] -> [(3,2),(2,1),(3,1)]
		Node a = new Node(3);
		Node b = new Node(3);
		Node c = new Node(2);
		Node d = new Node(3);

		a.setNext(b);
		b.setNext(c);
		c.setNext(d);

		CompressingLinkedList l = new CompressingLinkedList();
		l.compress(a);
		System.out.println(l.toString());
	}
}
