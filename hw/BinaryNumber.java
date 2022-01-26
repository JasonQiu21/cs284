import Java.util.Arrays;

public class BinaryNumber {
	private int[] bin;
	
	//creates  a  binary  number  of  length "length" and consisting only of zeros
	public BinaryNumber(int length){
		bin = new int[length];
		if(length <= 0){
			throw new IllegalArgumentException("length must be >= 0.");
		}
	}
	
	//creates  a  binary  number  using a string
	public BinaryNumber(String str){
		bin = new int[str.length()];
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
		return this.bin.length;
	}
	
	//returns the integer array representing a binary number
	public int[] getInnerArray(){
		return this.bin;
	}
	
	//returns a digit of a binary number given an index.
	public int getDigit(int index){
		return this.bin[index];
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
		a.bitShift(-1, 1);
		System.out.println(a.toString());
	}
}
