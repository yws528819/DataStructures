package search;

public class InsertValueSearch {

    public static void main(String[] args) {
        int arr[] = {1, 8, 10, 89,1000,1000, 1234};
        int index = insertValueSearch(arr, 0, arr.length - 1, 1000);
        if (index == -1){
            System.out.println("没找到");
        }else {
            System.out.println("下标为" + index);
        }
    }

    public static int  insertValueSearch(int[] arr, int left, int right, int findVal) {
        if (left > right || findVal < arr[left] || findVal > arr[right]) {
            return -1;
        }

        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);

        if (findVal > arr[mid]) {
            return insertValueSearch(arr, left, mid - 1, findVal);
        }else if(findVal > arr[mid]) {
            return insertValueSearch(arr, mid + 1, right, findVal);
        }else {
            return mid;
        }

    }

}
