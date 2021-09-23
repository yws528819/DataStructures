package search;

import java.util.Arrays;

public class FibonacciSearch {

    public static void main(String[] args) {
        int [] arr = {1,8, 10, 89, 1000, 1234};
        System.out.println("index=  " + fibSearch(arr, 1000));
    }

    /**
     * 生成斐波那契数列（黄金分割的数列，每一个代表查找数组的下标）
     * @param maxSize
     * @return
     */
    public static int[] fib(int maxSize) {
        int[] fn = new int[maxSize];
        fn[0] = 1;
        fn[1] = 1;
        for (int i=2; i<maxSize; i++) {
            fn[i] = fn[i-1] + fn[i-2];
        }
        return fn;
    }




    public static int fibSearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;

        //先生成一个黄金分割数列
        int[] f = fib(20);


        //先把查找的数组，与黄金分割数列校正
        int k = 0;
        while (high > f[k] - 1) {
            k++;
        }
        //扩展查找的数组，补0
        int[] temp = Arrays.copyOf(arr, k);
        //扩展的位值补排序数组最大的值
        //temp = {1,8, 10, 89, 1000, 1234, 0, 0}  => {1,8, 10, 89, 1000, 1234, 1234, 1234}
        for(int i=high+1; i<temp.length; i++) {
            temp[i] = arr[high];
        }


        while (low <= high) {
            int mid = low + f[k-1] - 1;

            if (key < temp[mid]) {
                high = mid - 1;
                //为什么是k-1;
                //全部元素 = 前面的元素 + 后边元素
                //f[k] = f[k-1] + f[k-2]
                //这里查找的值在f[k-1]里，类推公式及f[k-1] = f[k-2] + f[k-3]
                //推算mid应为 k 用k-1代替
                //mid = low + f[(k-1)-1] - 1;
                k--;
            }else if (key > temp[mid]) {
                low = mid + 1;
                // k = k-2?
                //这里查找的值在f[k-2]里，类推公式及f[k-2] = f[k-3] + f[k-4]
                //推算mid应为 k 用k-1代替
                //mid = low + f[(k-2)-1] - 1;
                k =-2;
            }else {
                if (mid <= high) {
                    return mid;
                }else {
                    return high;
                }
            }
        }
        return -1;
    }

}
