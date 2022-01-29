import Java.util.Arrays;

public class BinaryNumber {
	private int[] bin;
	private int length;
	
	//creates  a  binary  number  of  length "length" and consisting only of zeros
	public BinaryNumber(int length){
		this.length = length;
		bin = new int[length];
		if(length <= 0){
			throw new IllegalArgumentException("length must be >= 0.");
		}
	}
	
	//creates  a  binary  number  using a string
	public BinaryNumber(String str){
		bin = new int[str.length()];
		this.length = str.length();
		for(int i = 0; i < str.length(); i++){
			if(str.charAt(i) == "1".charAt(0)){
				bin[i] = 1;
			} else if(str.charAt(i) == "0".charAt(0)){
				bin[i] = 0;
			} else {
				throw new IllegalArgumentException("str must be a binary number.");
			}
		}
	}
	
	//returns the length of a binary
	public int getLength(){
		return this.length;
	}
	
	//returns the integer array representing a binary number
	public int[] getInnerArray(){
		return this.bin;
	}
	
	//returns a digit of a binary number given an index.
	public int getDigit(int index){
		try{
			return this.bin[index];
		} catch (IndexOutOfBoundsException e){
			throw new IndexOutOfBoundsException("Index out of bounds.");
		}
	}
	
	//returns a binary number in its decimal notation
	public int toDecimal(){
		int out = 0;
		int j = 1;
		for(int i = bin.length-1; i>=0; i--){
			if(bin[i]==1){
				out += j;
			}
			j *= 2;
		}
		return out;
	}
	
	//returns a binary number with all the digits shifted any number of places to the left or right.
	//The direction parameter indicates:
	//a left shift when the value is -1, right shift when the value is 1
	public void bitShift(int direction, int amount) {
		int[] temp;
		if(amount <=0){ throw new IllegalArgumentException("amount must be a positive integer.");}
		if(direction==-1){
			temp = new int[bin.length + amount];
			for(int i = 0; i<bin.length; i++){
				temp[i] = bin[i];
			}
		} else if(direction==1){
			temp = new int[bin.length-amount];
			for (int i = 0; i < bin.length-amount; i++) {
				temp[i] = bin[i];
			}
		} else {
			throw new IllegalArgumentException("direction must be -1 or 1");
		}
		bin = temp;
	}
	
	public static int[] bwor(BinaryNumber bn1, BinaryNumber bn2) {
		if(bn1.getLength() != bn2.getLength()){throw new IllegalArgumentException("bn1 and bn2 must be the same length.");}
		int[] out = new int[bn1.getLength()];
		int[] a = bn1.getInnerArray();
		int[] b = bn2.getInnerArray();
		for(int i = 0; i < bn1.getLength(); i++){
			if(a[i] == 1 || b[i] == 1){
				out[i] = 1;
			}
			
		}
		return out;
	}
	
	public static int[] bwand(BinaryNumber bn1, BinaryNumber bn2) {
		if(bn1.getLength() != bn2.getLength()){throw new IllegalArgumentException("bn1 and bn2 must be the same length.");}
		int[] out = new int[bn1.getLength()];
		int[] a = bn1.getInnerArray();
		int[] b = bn2.getInnerArray();
		for(int i = 0; i < bn1.getLength(); i++){
			if(a[i] == 1 && b[i] == 1){
				out[i] = 1;
			}
			
		}
		return out;
	}
	public void add (BinaryNumber aBinaryNumber){
		int carry = 0;
		int[] a = this.getInnerArray();
		int[] b = aBinaryNumber.getInnerArray();
		int digit;
		int[] temp;
		
		//Eqaulize different length binary nums
		int diff = this.getLength() - aBinaryNumber.getLength();
		if(diff != 0){
			if(this.getLength() > aBinaryNumber.getLength()){
				temp = new int[a.length+diff];
				for (int i = 0; i < a.length; i++) {
					temp[i+diff] = a[i];
				}
				a = temp;
			} else {
				temp = new int[b.length+diff];
				for (int i = 0; i < b.length; i++) {
					temp[i+diff] = b[i];
				}
				b = temp;
			}
		}
		for(int i=a.length-1; i>0; i--){
			int digitSum = a[i] + b[i] + carry;
			if(digitSum > 1){carry = 1;}
			if(digitSum == 1 || digitSum == 3){digit = 1;} else {digit = 0;}
			a[i] = digit;
		}
		if(carry == 1){				
			temp = new int[a.length + 1];
			for (int i = 0; i < b.length; i++) {
				temp[i+1] = a[i];
			}
			temp[0] = 1;
			this.bin = temp;}
	}
	
	public String toString(){
		return java.util.Arrays.toString(this.getInnerArray());
	}

	public static void main(String[] args) {
		BinaryNumber a = new BinaryNumber("1101");
		BinaryNumber b = new BinaryNumber("1011");
		System.out.println(a.getLength());
		System.out.println(a.getDigit(2));
		System.out.println(a.getInnerArray()[0]);
		System.out.println(a.toDecimal());
		System.out.println(java.util.Arrays.toString(bwor(a, b)));
		System.out.println(java.util.Arrays.toString(bwand(a, b)));
		a.add(b);
		System.out.println(a.toString());
		a.bitShift(-1, 1);
		System.out.println(a.toString());
	}
}
