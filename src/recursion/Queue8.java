package recursion;

public class Queue8 {
    //定义一个max表示共有多少个皇后
    int max = 8;
    int[] arry = new int[max];
    int count = 0;
    int judgeCount = 0;

    public static void main(String[] args) {

        Queue8 queue8 = new Queue8();
        queue8.check(0);
    }

    private void check(int n) {
        if (n == max) {
            print();
            return;
        }

        for(int i=0; i<max; i++) {
            arry[n] = i;
            if (judge(n)) {
                check(n + 1);
            }
        }



    }

    private void print() {
        count ++;
    }


    private boolean judge(int n) {


        return true;
    }
}
