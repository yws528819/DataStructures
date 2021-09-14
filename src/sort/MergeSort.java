package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class MergeSort {

    public static void main(String[] args) {
        //int[] arr = new int[]{83, 61, 76, 61, 63, 20, 32, 39};

        int[] arr = new int[80000];
        for (int i=0; i<arr.length; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = new Date();
        System.out.println("排序前的时间是：" + sdf.format(d1));

        //System.out.println("排序前：" + Arrays.toString(arr));
        mergeSort(arr, 0, arr.length-1, new int[arr.length]);
        //System.out.println("排序后：" + Arrays.toString(arr));
        Date d2 = new Date();
        System.out.println("排序后的时间是：" + sdf.format(d2));
    }


    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            //最小粒度(0,1)(0,2)...：比如(0,1),往下走第一个mergeSort(0,0)，第二个mergeSort(1,1)都不满足left<right，直接到merge
            //分治
            int mid = (left + right) / 2;
            //分
            mergeSort(arr, left, mid, temp);
            mergeSort(arr, mid + 1, right, temp);
            //治
            merge(arr, left, right, temp);
        }
    }


    /**
     * 归并
     * @param arr 排序数组
     * @param left 左边部分
     * @param right 右边部分
     * @param temp 辅助数组
     */
    public static void merge(int[] arr, int left, int right, int[] temp) {
        int mid = (left + right) / 2;
        //左边部分起始位置
        int l = left;
        //右边部分起始位置
        int r = mid + 1;

        //temp数组起始位置
        int t = 0;

        //左右两部分，从头开始比较，小的放入辅助数组，进一位，依次比较直到有一方比完
        while (l <= mid && r <= right) {
            if (arr[l] < arr[r]) {
                temp[t] = arr[l];
                l++;
                t++;
            }else {
                temp[t] = arr[r];
                r++;
                t++;
            }
        }

        //左右两部分，还有残留的一方，按下标顺序放入辅助数组，递归的原因，已经是排好序的了
        while (l <= mid) {
            temp[t] = arr[l];
            l++;
            t++;
        }

        while (r <= right) {
            temp[t] = arr[r];
            r++;
            t++;
        }

        //将辅助数组里排序好的数字，放入到排序数组对应的位置
        //比如：arr[2] arr[3]  对应 temp[0] temp[1]
        int leftTmp = left;
        t = 0;
        while (leftTmp <= right) {
            arr[leftTmp] = temp[t];
            leftTmp ++;
            t ++;
        }

    }
}
