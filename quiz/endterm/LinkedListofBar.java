/** the bar consists of 3 data fields:
* 1. bar type, which is either stone or salt;
* 2. height , which is a positive integer of the bar, you may assume all stone bars have unique heights; 
* 3. next, which points to the next bar;
*/
class Bar { 
		
	String type;
	Integer height; 
	Bar next;

	public Bar(String barType , Integer barHeight) {
	    type = barType; 
		height = barHeight;
	} 
}

public class LinkedListofBar {

	Bar head; // the head of the linked list
	public LinkedListofBar(Bar newHead) {
		this.head = newHead; 
	}
	
	/**
	* get the water mass and concentration of the pool
	* @return an array {waterMass, concentration}, where waterMass is
	* the mass of water in the pool, and concentration = saltMass / waterMass */
	public double[] fillInWater() {
		
		if (this.head == null) 
			return new double [] {};	
		findTallest(this.head);	
		findSecondTallest(this.head);
		return computeMassAndConcentration ();
	}
	
	
	/** step 1: finding the height and the pointer 
	 *  to the tallest bar
	 */
	Bar tallestNode = null; // the reference to the tallest node;
	int tallestIdx = -1; // the index of the tallest node in the linked list; 
	int topHeight = -1; // the height of the tallest node;
	
	public void findTallest(Bar node) {
	
		/** You can use the while loop to compute:
		 * 1. topHeight: an integer variable declared above which is the height of the tallest node , e.g., 5;
		 * 2. tallestNode: a Bar object declared above referencing the tallest node;
		 * 3. tallestIdx: an integer variable declared above which is the index of the tallest node , e.g., 1;
		 */
		int i = 0;
		while(node.next != null){
			if(node.type == "stone"){
				if(node.height > topHeight){
					tallestNode = node;
					topHeight = node.height;
					tallestIdx = i;
				}
			}
			i++;
			node = node.next;
		}
		if(node.type == "stone"){
			if(node.height > topHeight){
				tallestNode = node;
				topHeight = node.height;
				tallestIdx = i;
			}
		}
	}
	
	
	/** step 2: finding the height and the pointer to the 2nd tallest bar
	 */
	Bar secondTallestNode = null; // the reference to the 2nd tallest node;
	int secondTallestIdx = -1; // the index of the 2nd tallest node in the linked list; 
	int secondHeight = -1; // the height of the second tallest node;
	
	public void findSecondTallest(Bar node) {
		/**  You can use the while loop to compute:
		* 1. secondheight: an integer variable declared above which is the height of the 2nd tallest node , e.g., 4;
		* 2. secondTallestNode: the Bar object declared above referencing the 2nd tallest node;
		* 3. secondTallestIdx: an integer variable declared above which is the index of the 2nd tallest node , e.g., 5;
		*/
		int i = 0;
		while(node.next != null){
			if(node.type == "stone"){
				if(node.height > secondHeight && node.height < topHeight){
					secondTallestNode = node;
					secondTallestIdx = i;
					secondHeight = node.height;
				}
			}
			node = node.next;
			i++;
		}
		if(node.type == "stone"){
				if(node.height > secondHeight && node.height < topHeight){
					secondTallestNode = node;
					secondTallestIdx = i;
					secondHeight = node.height;
				}
			}
	}


	/** step 3: compute the water mass and the concentration of the salt water pool
	 * @return the water mass and the concentration in the form of list
	 */
	public double[] computeMassAndConcentration() {
		double tw = 0.0;
		double ts = 0.0; //Total water, total salt
		Bar node = (secondTallestIdx > tallestIdx) ? tallestNode : secondTallestNode;
		Bar end = (node == tallestNode) ? secondTallestNode : tallestNode;
		node = node.next;
		while(node != end){
			if(node.type == "stone"){
				tw += secondHeight - node.height;
			} else if(node.type == "salt"){
				tw += secondHeight;
				ts += node.height;
			}
			node = node.next;
		}
		return new double[]{tw, ts/tw};
	}
	
	public static void main(String[] args) {
		Bar bar1 = new Bar("stone", 2);
		Bar bar2 = new Bar("stone", 3);
		Bar bar3 = new Bar("stone", 1);
		Bar bar4 = new Bar("salt", 2);
		Bar bar5 = new Bar("salt", 1);
		Bar bar6 = new Bar("stone", 4);
		bar1.next = bar2;
		bar2.next = bar3;
		bar3.next = bar4;
		bar4.next = bar5;
		bar5.next = bar6;
		LinkedListofBar barList = new LinkedListofBar(bar1);
		double[] results = barList.fillInWater();
		for(double i: results){System.out.println(i);}
	}
}