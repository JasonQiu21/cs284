public class BinaryNumber {
	private int[] bin;
	
	//creates  a  binary  number  of  length "length" and consisting only of zeros
	public BinaryNumber(int length){
		if(length < 0){
			throw new IllegalArgumentException("length must be >= 0.");
		} else {
			for(int i = 0; i < length; i++){
				bin[i] = 0;
			}
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
		return 0;
	}
	
	//returns a binary number with all the digits shifted any number of places to the left or right.
	//The direction parameter indicates:
	//a left shift when the value is -1, right shift when the value is 1
	public void bitShift(int direction, int amount) {
	}
	
	public static int[] bwor(BinaryNumber bn1, BinaryNumber bn2) {
		return new int[]{};
	}
	
	public static int[] bwand(BinaryNumber bn1, BinaryNumber bn2) {
		return new int[]{};
	}
	
	public void add (BinaryNumber aBinaryNumber){
	}
	
	public String toString(){
		return "";
	}

	public static void main(String[] args) {
		BinaryNumber a = new BinaryNumber("1101");
		// BinaryNumber b = new BinaryNumber("1011");
		System.out.println(a.getLength());
		System.out.println(a.getDigit(2));
		System.out.println(a.getInnerArray()[0]);
	}
}
