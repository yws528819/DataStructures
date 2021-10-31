package com.yws.prim;

public class PrimAlgorithm {
    public static void main(String[] args) {
        //测试看看图是否创建ok
        char[] data = new char[]{'A','B','C','D','E','F','G'};
        int verxs = data.length;
        //邻接矩阵的关系使用二维数组表示,10000这个大数，表示两个点不联通
        int [][]weight=new int[][]{
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000},};

        MGraph mGraph = new MGraph(verxs, data, weight);
        mGraph.prim(0);
    }


}


class MGraph {
    private int verxs;
    private char[] data;
    private int[][] weights;

    public MGraph(int verxs, char[] data, int[][] weights) {
        this.verxs = verxs;
        this.data = data;
        this.weights = weights;
    }


    /**
     * 普里姆算法
     * @param v
     */
    public void prim(int v) {

        int[] visited = new int[verxs];
        visited[v] = 1;

        int min = Integer.MAX_VALUE;

        int h1 = -1;
        int h2 = -1;

        //这一层遍历，主要是被访问的节点，准备创建最小生成树
        for (int k=1; k<verxs; k++) {

            for (int i=0; i<verxs; i++) {
                for(int j=0; j<verxs; j++) {
                    //被访问的端点 与 未访问的端点 组成边，且是最小边
                    //比如第一次肯定只有一个visited[i]=1，随着最外层的循环慢慢增多
                    if (visited[i] == 1 && visited[j] == 0 && weights[i][j] < min) {
                        min = weights[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            //每次循环完，在所有关联边里，拿最小边
            visited[h2] = 1;
            System.out.println("找到边" + data[h1] + "-" + data[h2] + "> 权值：" + min );
            min = Integer.MAX_VALUE;

        }


    }


}
