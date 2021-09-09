package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class InsertSort {
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
        insertSort(arr);
        //System.out.println("排序后：" + Arrays.toString(arr));
        Date d2 = new Date();
        System.out.println("排序后的时间是：" + sdf.format(d2));
    }


    /**
     * 往左侧比较，第1轮【下标1】先跟下标0比较，小的往左侧交换，第2轮【下标2】再跟下标1、下标0比较...左侧永远是从小到大的
     * @param arr
     */
    public static void insertSort(int[] arr) {
        //每轮的值
        int cmpVal;
        //开始比较的位置
        int startIndex;

        for (int i=1; i<arr.length; i++) {
            cmpVal = arr[i];
            startIndex = i-1;
            //跟左侧比较，如果有更小的（左侧已经是从小到大了），左侧的数据就往右边移动
            //一直到没得比或者已经比左侧大了，就停止比较
            while (startIndex >= 0 && arr[startIndex] > cmpVal) {
                arr[startIndex + 1] = arr[startIndex];
                //记录最后交换的位置
                startIndex --;
            }
            //如果交换的位置变动了，就做下交换
            if (startIndex + 1 != i) {
                arr[startIndex + 1] = cmpVal;
            }
        }

    }

    /**
     * 直接左侧比较，把左侧当成一个独立的数组，去移动
     * @param arr
     */
    public static void insertSort2(int[] arr) {
        for (int i=1; i<arr.length; i++) {
            int temp;

            for (int j=i; j>0; j--) {
                if (arr[j] < arr[j-1]) {
                    temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;
                }else {
                    break;
                }
            }
        }

    }
}
