import java.util.ArrayList;

public class TupleSort {
	
   class Tuple implements Comparable<Tuple>{
	    int[] tuple;
		
		public Tuple(int[] this_tuple) {
			this.tuple = this_tuple;
		}

		/** you need to implement this function */
		@Override
		public int compareTo(Tuple other_tuple) {
			int length = Math.min(tuple.length, other_tuple.tuple.length);
			for(int i=0; i<length; i++){
				if(tuple[i] < other_tuple.tuple[i]){return -1;}
				else if (tuple[i] > other_tuple.tuple[i]){return 1;}
			}
			if(length == tuple.length && length == other_tuple.tuple.length){return 0;}
			else if(length == tuple.length){return -1;} // This tuple is shorter, all values equal
			else{return 1;}

		}
	}
   
    public TupleSort() {
		
    }
    
	public int test_compareTo_gs(int[] arr1, int[] arr2) {	
		Tuple t1 = new Tuple(arr1);
		Tuple t2 = new Tuple(arr2);		
		return t1.compareTo(t2);
	}
	
	public ArrayList<int[]> test_tuplesort_gs(ArrayList<int[]> list) {	
		int len = list.size();
			Tuple[] tuple = new Tuple[len];
			for (int i = 0; i < len; i++) {
				tuple[i] = new Tuple(list.get(i));
			}		   
			Tuple[] sorted_tuple = tuple_sort (tuple);
			
			ArrayList<int[]> sorted_list = new ArrayList<int[]>();	  
			for (int i = 0; i < len; i++) {
				if (sorted_tuple[i] != null)
					sorted_list.add(sorted_tuple[i].tuple);
				else
					sorted_list.add(new int[]{-1});
			}	
			
			return sorted_list;
	}
	
   
   /** you need to implement this function */
   public Tuple[] tuple_sort(Tuple[] array) {
	   int n = array.length;
	   for(int i = 1; i<n; i++){
		   //Inerstion sort step
		   int j = i;
		   while(j>0 && array[j].compareTo(array[j-1])<0){
			   //Swap
			   Tuple temp = array[j];
			   array[j] = array[j-1];
			   array[j-1] = temp;
			   j--;
		   }
	   }
	   return array;
   }
   
   public void print_tuple_array(Tuple[] array) {
	   
	   String actual_output = "";
	   
	   for(int i = 0; i < array.length; i++) {
		   
		   String output = "(";
		   for(int x = 0; x < array[i].tuple.length; x++)
			   output += array[i].tuple[x] + ", ";
		   output = output.substring(0, output.length()-2);
		   
		   output += ")";
		   
		   actual_output += output + ", ";
		   
	   }
	   
	   System.out.println(actual_output.substring(0, actual_output.length()-2));
	   
   }
   
   public void test_tuple_sort () {
	   Tuple [] test_tuple = new Tuple [5];
	   test_tuple [0] = new Tuple (new int[] {1 , 2});
	   test_tuple [1] = new Tuple (new int[] {2});
	   test_tuple [2] = new Tuple (new int[] {1 , 1 , 1});
	   test_tuple [3] = new Tuple (new int[] {1 , 5 , 0 , 5});
	   test_tuple [4] = new Tuple (new int[] {1 , 5 , -1});
	   System.out.println("Before sorting: ");
	   this.print_tuple_array(test_tuple);
	   Tuple [] sorted_tuple = this. tuple_sort ( test_tuple );
	   System.out.println("After sorting: ");
	   this.print_tuple_array(test_tuple);
   }
   
   public static void main(String[] args) {
	   TupleSort app = new TupleSort();
	   app.test_tuple_sort();
   }

}