package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ShellSort {
    public static void main(String[] args) {
        int[] arr = new int[80000];
        for (int i=0; i<arr.length; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        //int[] arr = new int[]{83, 6, 76, 61, 63, 20, 32, 39};

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = new Date();
        System.out.println("排序前的时间是：" + sdf.format(d1));

        //System.out.println("排序前：" + Arrays.toString(arr));
        shellSort(arr);
        //System.out.println("排序后：" + Arrays.toString(arr));
        Date d2 = new Date();
        System.out.println("排序后的时间是：" + sdf.format(d2));
    }


    public static void shellSort(int[] arr) {
        for(int step = arr.length / 2; step > 0; step /= 2) {

            for (int j=step; j<arr.length; j++) {
                int temp = arr[j];
                int cmpIndex = j-step;
                if (temp < arr[cmpIndex]) {
                    while (cmpIndex >= 0 && temp < arr[cmpIndex]) {
                        arr[cmpIndex + step] = arr[cmpIndex];
                        cmpIndex -= step;
                    }
                }
                arr[cmpIndex + step] = temp;

            }
        }
    }

}
