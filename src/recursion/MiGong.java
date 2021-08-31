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

        //设置障碍墙
        map[3][1] = 1;
        map[3][2] = 1;
        //map[1][2] = 1;
        //map[2][2] = 1;

        setWay(map, 1, 1);

        //输出新的地图, 小球走过，并标识过的递归
        System.out.println("小球走过，并标识过的 地图的情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }



    }



    private static boolean setWay(int[][] map, int x, int y) {
        if (map[6][5] == 2) {//通路已找到ok
            return true;
        }
        if (map[x][y] == 0) {
            map[x][y] = 2;
            if (setWay(map, x+1, y)) {//向下走
                return true;
            }else if (setWay(map, x, y+1)) {//向右走
                return true;
            }else if (setWay(map, x-1, y)) {//向上走
                return true;
            }else if (setWay(map, x, y-1)) {//向左走
                return true;
            }else {
                //说明该点是走不通，是死路
                map[x][y] = 3;
                return false;
            }
        }else {
            return false;
        }
    }


}
