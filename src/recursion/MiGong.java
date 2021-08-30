package recursion;

public class MiGong {

    public static void main(String[] args) {
        // 先创建一个二维数组，模拟迷宫
        int [][] map = new int[8][7];

        //使用1表示墙
        //首尾行、首尾列设置成墙
        for (int i=0; i<7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i=0; i<8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //输出地图
        for (int i=0; i<8; i++) {
            for(int j=0; j<7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }



    private static boolean setWay() {


        return false;
    }


}
