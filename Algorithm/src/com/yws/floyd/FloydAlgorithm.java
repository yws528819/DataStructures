package com.yws.floyd;

import java.util.Arrays;

/**
 * 弗洛伊德算法
 * 分别算出各顶点到其他顶点的最短距离
 */
public class FloydAlgorithm {

    public static void main(String[] args) {
        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        //创建邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[] { 0, 5, 7, N, N, N, 2 };
        matrix[1] = new int[] { 5, 0, N, 9, N, N, 3 };
        matrix[2] = new int[] { 7, N, 0, N, 8, N, N };
        matrix[3] = new int[] { N, 9, N, 0, N, 4, N };
        matrix[4] = new int[] { N, N, 8, N, 0, 5, 4 };
        matrix[5] = new int[] { N, N, N, 4, 5, 0, 6 };
        matrix[6] = new int[] { 2, 3, N, N, 4, 6, 0 };

        Graph graph = new Graph(matrix, vertex);

        graph.floyd();
        graph.show();
    }


}

class Graph {
    private char[] vertex;
    private int[][] dis;
    private int[][] pre;


    public Graph(int[][] matrix, char[] vertex) {
        int length = vertex.length;
        this.dis = matrix;
        this.pre = new int[length][length];
        this.vertex = vertex;
        //pre数组初始化，存放的是顶点下标
        for (int i=0; i<length; i++){
            Arrays.fill(pre[i], i);
        }
    }

    /**
     * 弗洛伊德算法
     * k 是中间顶点
     * i 是出发顶点
     * j 是中间顶点
     * 三层遍历分别比较计算出i-k-j的最近距离（可能是ij也有可能是ik+ij）
     */
    public void floyd() {
        int len = 0;
        for(int k=0; k<dis.length; k++) {
            for (int i=0; i<dis.length; i++) {
                for (int j=0; j<dis.length; j++) {
                    len = dis[i][k] + dis[k][j];
                    if (len < dis[i][j]) {
                        dis[i][j] = len;
                        pre[i][j] = pre[k][j];//这里为什么不是k??
                    }
                }
            }
        }
    }

    public void show() {
        for (int i=0; i<dis.length; i++) {
            for (int index : pre[i]) {
                System.out.printf("%-5s",vertex[index]);
            }
            System.out.println();
        }

        System.out.println("=================");

        for (int i=0; i<dis.length; i++) {
            for (int index : dis[i]) {
                System.out.printf("%-5d", index);
            }
            System.out.println();
        }
    }
}
