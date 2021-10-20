package com.yws.binarysearchnorecursion;

public class BinarySearchNoRecur {

    public static void main(String[] args) {
        int[] arr = {1,3, 8, 10, 11, 67, 100};
        binarySearch(arr, 100);
    }

    /**
     * 二分查找非递归实现
     * @param arr
     * @param val
     */
    public static void binarySearch(int[] arr, int val) {
        int left = 0;
        int right = arr.length - 1;
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (arr[mid] == val) {
                System.out.println("查找的下标为：" + mid);
                break;
            }else if (val < arr[mid]) {
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }
    }

}
