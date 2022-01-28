public class IntArray{
    private int[] arr;

    public IntArray(int inputArr[]) {
        arr = inputArr;
    }

    public int getMax() {
        int tmp = arr[0];
        for(int i:arr){
            if(i > tmp){
                tmp = i;
            }
        }
        return tmp;
    }

    public static void main(String[] args) {
        int[] arrayA = {4,5,6,7};
        IntArray a = new IntArray(arrayA);
        System.out.println(a.getMax());
    }
}