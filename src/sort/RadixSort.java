package sort;

import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        int arr[] = { 53, 3, 542, 748, 14, 214};
        radixSort(arr);
    }



    public static void radixSort(int[] arr) {
        //找出数组里最大的数字
        int maxNumber = 0;
        for(int m=0; m<arr.length; m++) {
            if (arr[m] > maxNumber) {
                maxNumber = arr[m];
            }
        }
        //最大数字的长度
        int maxLength = (maxNumber + "").length();

        //数字0-9，10个桶
        int[][] bucket = new int[10][arr.length];

        int[] recordBuk = new int[10];

        //n代表要排几轮，r在取个、十、百...位数的时候用
        for (int n = 0, r=1; n < maxLength; n++, r=r*10) {
            for (int i=0; i<arr.length; i++) {
                //第几个桶
                int bIndex = (arr[i] / r) % 10;
                //recordBuk[bIndex] 代表桶里有多少个
                //放入桶里
                bucket[bIndex][recordBuk[bIndex]] = arr[i];
                //记录下当前桶放到第几个
                recordBuk[bIndex] ++;
            }

            //桶里的数字，按桶顺序依次拿出来，放入排序数组中
            int index = 0;
            for (int j=0; j<10; j++) {
                int tIndex=0;
                //不等于0，代表有当前桶有放数字
                if (recordBuk[j] != 0) {
                    while (tIndex < recordBuk[j]) {
                        arr[index] = bucket[j][tIndex];
                        tIndex++;
                        index++;
                    }
                    recordBuk[j] = 0;
                }
            }

            System.out.println("第" + n + "轮排序为" + Arrays.toString(arr));
        }
    }
}
