package sort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BubbleSort {

    public static void main(String[] args) {
        //int[] arr = new int[] {4,3,2,1,0};
        int[] arr = new int[80000];
        for (int i=0; i<arr.length; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = new Date();
        System.out.println("排序前的时间是：" + sdf.format(d1));

        //System.out.println("排序前：" + Arrays.toString(arr));
        bubbleSort(arr);
        //System.out.println("排序后：" + Arrays.toString(arr));
        Date d2 = new Date();
        System.out.println("排序后的时间是：" + sdf.format(d2));

    }


    public static void bubbleSort(int[] arr) {
        int temp;
        boolean finFlag;

        //每一轮最大的数就移到最后一位
        for (int i=0; i<arr.length-1; i++) {
            finFlag = true;
            //每一轮就可以少比较一位
            for(int j=0; j<arr.length-1-i; j++) {
                if (arr[j] > arr[j+1]) {
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    finFlag = false;
                }
            }
            //已经正常排序了，可能提前排好了，不再遍历
            if (finFlag) {
                break;
            }

        }
    }

}
