package graph;

import java.util.ArrayList;

public class Graph {

    private ArrayList<String> vertexList;//存储顶点集合
    private int[][] edges;//图对应的临接矩阵
    private int numOfEdges;//表示边的数目
    private boolean[] isVisited;//表示节点是否被访问

    public Graph(int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        isVisited = new boolean[n];
    }

    //返回结点的个数
    public int getNumOfVertext() {
        return vertexList.size();
    }

    //返回边的个数
    public int getNumOfEdges() {
        return numOfEdges;
    }

    /**
     * 插入结点
     * @param vertex
     */
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 添加边
     * @param v1
     * @param v2
     * @param weight
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges ++;
    }


    /**
     * 深度遍历
     */
    public void dfs() {
        for (int i=0; i<vertexList.size(); i++) {
            if (!isVisited[i]) {
                dfs(i, isVisited);
            }
        }
    }

    public void dfs(int i, boolean[] isVisited) {
        System.out.print(vertexList.get(i) + "->");
        isVisited[i] = true;
        int w = getFirstNeighbor(i);
        while (w != -1) {
            if (!isVisited[w]) {
                dfs(w, isVisited);
            }else {
                w = getNextNeighbour(i, w);
            }
        }
    }


    public int getFirstNeighbor(int i) {
        for (int j=0; j<vertexList.size(); j++) {
            if (edges[i][j] > 0) {
                return j;
            }
        }
        return -1;
    }


    public int getNextNeighbour(int i, int w) {
        for (int j=w+1; j<vertexList.size(); j++) {
            if (edges[i][j] > 0) {
                return j;
            }
        }
        return -1;
    }



    public static void main(String[] args) {
        String vertexs[] = {"A", "B", "C", "D", "E"};

        Graph graph = new Graph(vertexs.length);

        //添加节点
        for (String vertex : vertexs) {
            graph.insertVertex(vertex);
        }

        //添加边
        //A-B A-C B-C B-D B-E
		graph.insertEdge(0, 1, 1); // A-B
		graph.insertEdge(0, 2, 1); //
		graph.insertEdge(1, 2, 1); //
		graph.insertEdge(1, 3, 1); //
		graph.insertEdge(1, 4, 1); //

        graph.dfs();

    }





}
