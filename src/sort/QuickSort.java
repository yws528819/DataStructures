package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 快速排序（Quicksort）是对冒泡排序算法的一种改进。
 */
public class QuickSort {

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
        quickSort(arr, 0, arr.length-1);
        //System.out.println("排序后：" + Arrays.toString(arr));
        Date d2 = new Date();
        System.out.println("排序后的时间是：" + sdf.format(d2));
    }


    public static void quickSort(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        int midVal = arr[(left + right) / 2];
        int temp;

        while (l < r) {
            while (arr[l] < midVal) {
                l ++;
            }

            while (arr[r] > midVal) {
                r --;
            }

            if (l >= r) {
                break;
            }

            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //不然如果有值刚好等于中值，等到比较到这2个值时，会出现死循环,r和l不会--
            //如果交换完后，发现这个arr[l] == 中值 相等 r--， 前移
            if(arr[l] == midVal) {
                r -= 1;
            }
            //如果交换完后，发现这个arr[r] == 中值 相等 l++， 后移
            if(arr[r] == midVal) {
                l += 1;
            }
        }

        // 相等时重新调方法，会导致上面执行完l还是等于r，然后会重复调用方法导致栈溢出
        //比如 l == r ==1
        //会调用quickSort(arr, 0, 1)和quickSort(arr, 1, 2)
        //quickSort(arr, 1, 2)执行下还是l==r==1
        // 如果 l == r, 必须l++, r--, 否则为出现栈溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }

        if (left < r) {
            quickSort(arr, left, r);
        }

        if (right > l) {
            quickSort(arr, l, right);
        }

    }

}
