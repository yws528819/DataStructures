package graph;

import java.util.ArrayList;
import java.util.LinkedList;

public class BfsGraph {

    private ArrayList<String> vertexList;//存储顶点集合
    private int[][] edges;//图对应的临接矩阵
    private int numOfEdges;//表示边的数目
    private boolean[] isVisited;//表示节点是否被访问

    public BfsGraph(int n) {
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
     * 广度遍历
     */
    public void bfs() {
        //防止节点有些是独立的(猜测)
        for (int i=0; i<vertexList.size(); i++) {
            if (!isVisited[i]) {
                bfs(i, isVisited);
            }
        }
        System.out.println();
    }


    public void bfs(int i, boolean[] isVisited) {
        int u = 0;
        LinkedList<Integer> linkedList = new LinkedList<>();
        isVisited[i] = true;
        System.out.print(vertexList.get(i) + "=>");
        linkedList.addLast(i);

        while (linkedList.size() > 0) {
            //这里来控制图的连续性，0遍历完接着1、2、3...
            u = linkedList.removeFirst();
            int w = getFirstNeighbor(i);
            while (w != -1) {
                if (!isVisited[w]) {
                    isVisited[w] = true;
                    System.out.print(vertexList.get(w) + "=>");
                    linkedList.addLast(w);
                }
                w = getNextNeighbour(u, w);
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
        String vertexs[] = {"1", "2", "3", "4", "5", "6", "7", "8"};

        BfsGraph graph = new BfsGraph(vertexs.length);

        //添加节点
        for (String vertex : vertexs) {
            graph.insertVertex(vertex);
        }

        //更新边的关系
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);

        graph.bfs();

    }





}
