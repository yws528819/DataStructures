package sort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SelectSort {
    public static void main(String[] args) {
        //int[] arr = new int[] {41288, 63672, 72473, 40190, 4246, 34023, 3337, 72041};

        int[] arr = new int[80000];
        for (int i=0; i<arr.length; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = new Date();
        System.out.println("排序前的时间是：" + sdf.format(d1));

        //System.out.println("排序前：" + Arrays.toString(arr));
        selectSort(arr);
        //System.out.println("排序后：" + Arrays.toString(arr));
        Date d2 = new Date();
        System.out.println("排序后的时间是：" + sdf.format(d2));
    }


    /**
     * 每轮下标的值与右边的所有值比较，比较出最小的值，如果有比自己更小的，就跟自己交换位置
     * @param arr
     */
    public static void selectSort(int[] arr) {
        //每轮比较最小值的下标
        int minIndex;
        int temp;

        for (int i = 0; i < arr.length-1; i++) {
            //先赋值为每轮开始的下标
            minIndex = i;

            for(int j=i+1; j<arr.length; j++) {
                //如果存在更小的，找到对应的下标位置
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            //不等于，代表有更小的，交换到当前这轮的下标位置来
            if (i != minIndex) {
                temp = arr[minIndex];
                arr[minIndex] = arr[i];
                arr[i] = temp;
            }
        }


        //int minIndex;
        //int minVal;
        //
        //for (int i = 0; i < arr.length-1; i++) {
        //
        //    minIndex = i;
        //    minVal = arr[minIndex];
        //
        //    for(int j=i+1; j<arr.length; j++) {
        //        if (arr[j] < minVal) {
        //            minIndex = j;
        //            minVal = arr[j];
        //        }
        //    }
        //
        //    if (i != minIndex) {
        //        arr[minIndex] = arr[i];
        //        arr[i] = minVal;
        //    }
        //}
    }


}
