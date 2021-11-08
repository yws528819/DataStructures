package com.yws.kruskal;

/**
 * 克鲁斯卡尔算法
 */
public class KruskalCase {
    //使用 INF 表示两个顶点不能连通
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {

        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //克鲁斯卡尔算法的邻接矩阵
        int matrix[][] = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {   0,  12, INF, INF, INF,  16,  14},
                /*B*/ {  12,   0,  10, INF, INF,   7, INF},
                /*C*/ { INF,  10,   0,   3,   5,   6, INF},
                /*D*/ { INF, INF,   3,   0,   4, INF, INF},
                /*E*/ { INF, INF,   5,   4,   0,   2,   8},
                /*F*/ {  16,   7,   6, INF,   2,   0,   9},
                /*G*/ {  14, INF, INF, INF,   8,   9,   0}};

        KruskalCase kruskalCase = new KruskalCase(matrix, vertexs);

        kruskalCase.krusal();

    }

    private int[][] matrix;//邻接矩阵
    private char[] vertexs;//顶点数组
    private int edgeNum;//边的条数

    public KruskalCase(int[][] matrix, char[] vertexs) {
        this.matrix = matrix;
        this.vertexs = vertexs;

        for (int i=0; i<matrix.length; i++) {
            for (int j=i+1; j<matrix[i].length; j++) {
                if (matrix[i][j] != INF) {
                    edgeNum ++;
                }
            }
        }

    }


    public void krusal() {
        //根据邻接矩阵，找对应的边集合
        EData[] edges = getEdges(matrix);
        //边按权值升序排序
        sortEdge(edges);
        //边的端点对应的终点
        int[] ends = new int[edgeNum];

        for (int i=0; i<edges.length; i++) {
            //边
            EData edge = edges[i];
            //边的两端点，对应的索引
            int start = getPosition(edge.getStart());
            int end = getPosition(edge.getEnd());

            //两端点找各自的终点
            int m = getEnd(ends, start);
            int n = getEnd(ends, end);

            //看会不会构成回路
            if (m != n) {
                //得到所要的边
                System.out.println(edge);
                //加入终点集合，[终点1]=终点2，神奇的完善了每条边还带方向
                ends[m] = n;
            }

        }

    }




    /**
     * 创建边对象数组
     * @param matrix 邻接矩阵
     */
    private EData[] getEdges(int[][] matrix) {
        EData[] eDatas = new EData[edgeNum];

        int index = 0;
        for (int i=0; i<matrix.length; i++) {
            for (int j=i+1; j<matrix[i].length; j++) {
                if (matrix[i][j] != INF) {
                    //边对象创建，放入边对象集合里
                    eDatas[index ++] = new EData(vertexs[i], vertexs[j], matrix[i][j]);
                }
            }
        }

        return eDatas;
    }


    /**
     * 边对象数组按权值进行冒泡升序排序
     * @param eDatas
     */
    private void sortEdge(EData[] eDatas) {
        EData temp;
        for (int i=0; i<eDatas.length - 1; i++) {
            for (int j=0; j<eDatas.length - 1 - i; j++) {
                if (eDatas[j].getWeight() > eDatas[j + 1].getWeight()) {
                    temp = eDatas[j];
                    eDatas[j] = eDatas[j + 1];
                    eDatas[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 获得某一结点对应的终点，数组索引是起点，数组值是终点
     * 比如分别加入AB、CB、CD、DA
     * AB   类似放入   [A]=B
     * CB    =>      [C]=B
     * CD    =>      [B]=D
     * DA    =>      [D]=D //出现闭合了
     * @param ends
     * @param i
     * @return
     */
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }


    /**
     * 边端点的字符转成对应的数字索引，比如AB，A对应0， B对应1
     * @param ch
     * @return
     */
    private int getPosition(char ch) {
        for (int i=0; i<vertexs.length; i++) {
            if (vertexs[i] == ch) {
                return i;
            }
        }

        return -1;
    }












    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public char[] getVertexs() {
        return vertexs;
    }

    public void setVertexs(char[] vertexs) {
        this.vertexs = vertexs;
    }

    public int getEdgeNum() {
        return edgeNum;
    }

    public void setEdgeNum(int edgeNum) {
        this.edgeNum = edgeNum;
    }
}


/**
 * 边对象
 */
class EData {
    char start;//边的一个点
    char end;//边的另一个点
    int weight;//边的权值

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public char getStart() {
        return start;
    }

    public void setStart(char start) {
        this.start = start;
    }

    public char getEnd() {
        return end;
    }

    public void setEnd(char end) {
        this.end = end;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData{" +
                start +
                "->" + end +
                ", weight=" + weight +
                '}';
    }
}
