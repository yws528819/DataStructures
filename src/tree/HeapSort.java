package tree;

import java.util.Arrays;

public class HeapSort {

    public static void main(String[] args) {
        int[] arr = new int[8];
        for (int i = 0; i < 8; i++) {
            arr[i] = (int) (Math.random() * 100); // 生成一个[0, 8000000) 数
        }

        heapSort(arr);

        System.out.println(Arrays.toString(arr));
    }




    public static void heapSort(int[] arr) {
        //最后一个非叶子节点，总节点树的一半减1
        //从最后一个非叶子节点，由下往上，做一次大顶堆操作
        for (int j=arr.length/2 - 1; j>=0; j--) {
            adjustHeap(arr, j, arr.length);
        }

        //大顶堆做完后，都是第一个跟最后一个做交换
        //这里都是从第一个节点（跟节点）开始，做一次大顶堆调整
        //调整点的子树继续做调整即可，其他的无需再做，最前面已经做过了，不用再动
        int temp;
        for (int i=arr.length - 1; i>0; i--) {
            temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, i);

        }
    }


    /**
     * 大顶堆调整
     * @param arr 排序的数组
     * @param startIndex 调整起始点
     * @param length 调整数组长度，最后微调每次少一个位置
     */
    private static void adjustHeap(int[] arr, int startIndex, int length) {
        int temp = arr[startIndex];
        int k = 0;
        //调整交换点的子树继续比对调整
        for (k = startIndex * 2 + 1; k<length; k = k * 2 + 1) {
            //右节点比较大，指向右节点
            if (k+1 < length && arr[k] < arr[k + 1]) {
                k++;
            }

            //调整节点比右节点或左节点还大，右节点或左节点上浮，最后跟最后的右节点或者左节点做交换
            if (arr[k] > temp) {
                arr[startIndex] = arr[k];
                //这里用startIndex是因为如果没交换，temp还是要给arr[startIndex]
                startIndex = k;
            }else {
                break;
            }

        }

        arr[startIndex] = temp;

    }



}
