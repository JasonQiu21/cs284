import java.io.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

class The_Comparator implements Comparator<String> {
		
	/**
	* Step 1: Compare two large numbers 
	* return -1 if numberA > numberB;
	* return 1 if numberA < numberB;
	* return 0 if they are equal.
	*/
	public int compare(String numberA, String numberB)
	{
		char[] a = numberA.toCharArray();
		char[] b = numberB.toCharArray();
		if(a.length != b.length){
			return (a.length > b.length) ? -1 : 1;
		}
		for(int i = 0; i<a.length; i++){
			if(a[i] != b[i]){
				return (a[i] > b[i]) ? -1 : 1;
			}
		}
		return 0;
	}
}


public class ConcatNumbers {
		
	/**
	* Step 2: Check whether a large number concatenated by numberA and numberB is divisible by 3.
	* return true if the concatenated number can be divisible by 3
    * return false if the concatenated number can not be divisible by 3
    */	
	public boolean check_concatenation_dividable_by_three(String numberA, String numberB) {
		String s = numberA.concat(numberB);
		int x = 0;
		for(char i: s.toCharArray()){
			int y = i - '0';
			x += y;
		}
		return (x%3 == 0) ? true : false;
	}

	
	/**
	 * Step 3: 
	* @return a string list which contains the maximum K concatenated numbers which can be divisible by 3.
    */	
	public String[] KMaxConcatenations(String[] A, String[] B, int N, int K)
	{
		PriorityQueue<String> pq = new PriorityQueue<String>(new The_Comparator());
		
		for(String i: A){
			for(String j: B){
				if(check_concatenation_dividable_by_three(i, j)){
					pq.add(i.concat(j));
				}
			}
		}
		String[] out = new String[K];
		for(int i = 0; i < K; i++){
			if(pq.isEmpty()){
				return out;
			}
			out[i] = (pq.poll());
		}
		return out;
	}

	public static void main(String[] args) {
		String[] A = { "23252624", "65254255", "36235235", "01328903" };
		String[] B = { "53735734", "38491040", "17780767", "89890234" };
		int N = A.length;
		int K = 3;
		String[] expected = { "6525425538491040", "3623523589890234", "3623523553735734" };
		ConcatNumbers test = new ConcatNumbers();
		String[] results = test.KMaxConcatenations(A, B, N, K);
		for(String i: results){System.out.println(i);}
	}
}
