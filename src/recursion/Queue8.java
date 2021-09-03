package recursion;

public class Queue8 {
    //定义一个max表示共有多少个皇后
    int max = 8;
    //定义数组array,数组的第几位代表第几个皇后，数组第几位的值代表第几个皇后的位置
    int[] arry = new int[max];
    static int count = 0;
    static int judgeCount = 0;

    public static void main(String[] args) {

        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.printf("一共有%d解法 \n", count);
        System.out.printf("一共判断冲突的次数%d次 \n", judgeCount); // 1.5w
    }

    private void check(int n) {
        //最后一个皇后放好后，就返回，然后上一个皇后就继续下一个位置
        if (n == max) {
            print();
            return;
        }

        //返回后，这个皇后尝试下一个位置
        for(int i=0; i<max; i++) {
            //皇后尝试下一个位置
            arry[n] = i;
            //跟前面的皇后判断下位置，如果不对，就继续下个位置，可以，下一层皇后就从自己这一行第一个开始尝试
            if (judge(n)) {
                check(n + 1);
            }
        }
    }

    private void print() {
        count ++;
        for (int i = 0; i < arry.length; i++) {
            System.out.print(arry[i] + ",");
        }
        System.out.println();
    }


    /**
     * 查看当前皇后和前面的皇后位置是否有冲突（在同一列或者同一行或者斜角线）
     * @param n
     * @return
     */
    private boolean judge(int n) {
        judgeCount ++;
        for (int i=0; i<n; i++) {
            //同一列（肯定不会同一行），不在斜线上
            if (arry[n] == arry[i] || Math.abs(i-n) == Math.abs(arry[i] - arry[n])) {
                return false;
            }
        }
        return true;
    }
}
