package com.yws.dijkstra;

import java.util.Arrays;

/**
 * 克鲁斯卡尔算法
 * 求出某一个顶点，到其他顶点的最小路径
 */
public class DijkstraAlgorithm {

    public static void main(String[] args) {
        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        //邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;// 表示不可以连接
        matrix[0]=new int[]{N,5,7,N,N,N,2};
        matrix[1]=new int[]{5,N,N,9,N,N,3};
        matrix[2]=new int[]{7,N,N,N,8,N,N};
        matrix[3]=new int[]{N,9,N,N,N,4,N};
        matrix[4]=new int[]{N,N,8,N,N,5,4};
        matrix[5]=new int[]{N,N,N,4,5,N,6};
        matrix[6]=new int[]{2,3,N,N,4,6,N};


        int startIndex = 6;
        Graph graph = new Graph(vertex, matrix, startIndex);
        graph.dijk(startIndex);

        graph.showDijkstra();


    }




}


class Graph {
    private char[] vertex;
    private int[][] matrix;

    private int[] dis;
    private int[] already_arr;
    private int[] pre_arr;

    public Graph(char[] vertex, int[][] matrix, int index) {
        this.vertex = vertex;
        this.matrix = matrix;

        dis = new int[vertex.length];
        already_arr = new int[vertex.length];
        pre_arr = new int[vertex.length];

        Arrays.fill(dis, 65535);
        dis[index] = 0;
    }



    public void dijk(int index) {
        //按给定的初始顶点，更新与各顶点的距离
        update(index);

        //按其他顶点，一一更新，初始顶点与各顶点的距离
        for (int j=1; j<vertex.length; j++) {
            index = getNextIndex();
            update(index);
        }
    }



    /**
     * 通过跟初始顶点直接关联的顶点，也变相统计出了间接关联的顶点的距离
     * @param index
     */
    private void update(int index) {
        //比如按G顶点为初始顶点，这里index假设是B顶点
        int len = 0;
        for (int j=0; j<matrix[index].length; j++) {
            //这里是GB（可能是GB或者GA+GB） 加上 BC  如果小于  GC，就更新GC距离为（GB+BC）
            len = dis[index] + matrix[index][j];

            if (already_arr[j] == 0 && len < dis[j]) {
                dis[j] = len;
                pre_arr[j] = index;
            }
        }


    }

    /**
     * 这里去跟初始顶点，直接相连的顶点，按距离从小到大依次拿到未访问的顶点
     * @return
     */
    private int getNextIndex() {
        int len = 65535;
        int index = 0;
        for (int i=0; i<vertex.length; i++) {
            if (already_arr[i] == 0 && dis[i] < len) {
                len = dis[i];
                index = i;
            }
        }

        already_arr[index] = 1;
        return index;
    }



    public void showDijkstra() {
        System.out.println("==========================");
        //输出already_arr
        for(int i : already_arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        //输出pre_visited
        for(int i : pre_arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        //输出dis
        for(int i : dis) {
            System.out.print(i + " ");
        }
        System.out.println();
        //为了好看最后的最短距离，我们处理
        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        int count = 0;
        for (int i : dis) {
            if (i != 65535) {
                System.out.print(vertex[count] + "("+i+") ");
            } else {
                System.out.println("N ");
            }
            count++;
        }
        System.out.println();
    }
}
