package search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找，前提数组是有序的
 */
public class BinarySearch {

    public static void main(String[] args) {
        int arr[] = {1, 8, 10, 89,1000,1000, 1234 };
        List<Integer> integers = binarySearch(arr, 0, arr.length - 1, 0);
        if (integers.isEmpty()) {
            System.out.println("数组里查不到");
        }else {
            System.out.println("查找的值所在下标为：" + integers);
        }
    }

    /**
     * 二分查找算法
     * @param arr 查找的数组
     * @param left 左边的索引
     * @param right 右边的索引
     * @param searchVal 查找的值
     * @return
     */
    public static List<Integer> binarySearch(int[] arr, int left, int right, int searchVal) {

        if (left > right) {
            return new ArrayList<>();
        }
        //中间索引
        int midIndex = (left + right) / 2;
        //往右边找
        if (searchVal > arr[midIndex]) {
            //右边进行二分查找
            return binarySearch(arr, midIndex + 1, right, searchVal);
        }else if (searchVal < arr[midIndex]) {
            //左边进行二分查找
            return binarySearch(arr, left, midIndex - 1, searchVal);
        }else {
            //中间值刚好是查找值
            List<Integer> resultList = new ArrayList<>();
            resultList.add(midIndex);

            //右边遍历一遍有没有重复的查找值
            int tempIndex = midIndex + 1;
            while (tempIndex <= right) {
                if (arr[tempIndex] == searchVal) {
                    resultList.add(tempIndex);
                }
                tempIndex ++;
            }
            //左边遍历一遍有没有重复的查找值
            tempIndex = midIndex - 1;
            while (tempIndex >= left) {
                if (arr[tempIndex] == searchVal) {
                    resultList.add(tempIndex);
                }
                tempIndex --;
            }

            return resultList;
        }
    }
}
