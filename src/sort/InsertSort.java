package sort;

public class InsertSort {
    public static void main(String[] args) {

    }



    public static void insertSort(int[] arr) {
        int cmpVal;
        int startIndex;

        for (int i=1; i<arr.length; i++) {
            cmpVal = arr[i];
            startIndex = i-1;

            while (startIndex >= 0 && arr[startIndex] > cmpVal) {
                arr[startIndex + 1] = arr[startIndex];
                startIndex --;
            }

            if (startIndex + 1 != i) {
                arr[startIndex + 1] = cmpVal;
            }
        }
    }
}
