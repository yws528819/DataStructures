package com.yws.horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 马踏棋盘算法/骑士周游算法
 */
public class HorseChessboard {

    private static int X = 8;
    private static int Y = 8;

    private static boolean[] visited;
    private static boolean finished;


    public static void main(String[] args) {
        //马儿起始位置
        int row = 1;
        int col = 1;
        //创建棋盘
        int[][] chessboard = new int[X][Y];
        visited = new boolean[X * Y];//初始化访问值

        traversalChessboard(chessboard, row-1, col-1, 1);

        //输出棋盘最后情况
        for (int[] rows : chessboard) {
            for (int step : rows)  {
                System.out.print(step + "\t");
            }
            System.out.println();
        }

    }



    public static void traversalChessboard(int[][] chessboard, int row, int col, int step) {
        chessboard[row][col] = step;
        visited[row * X + col] = true;

        List<Point> ps = next(new Point(col, row));

        //这里走最佳步数的方案，体现出贪心算法，可节省大部分时间
        sort(ps);

        while (!ps.isEmpty()) {
            Point p = ps.remove(0);

            //判断改点是否已经访问过,p.y是行,p.x是列
            if (!visited[p.y * X + p.x]) {//这一步会在成功找到正确的方案后，所有的错误走法都不会再递归，直接跳过，确保了只有一种正确方案
                traversalChessboard(chessboard, p.y, p.x, step+1);
            }
        }

        if (step < X*Y && !finished) {
            chessboard[row][col] = 0;
            visited[row * X + col] = false;
        }else {
            finished = true;
        }
    }


    /**
     * 返回当前结点，马儿可以走的位置集合
     * @param curPoint
     * @return
     */
    public static List<Point> next(Point curPoint) {
        List<Point> ps = new ArrayList<>();

        Point p1 = new Point();

        //表示马儿可以走5这个位置
        if((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y -1) >= 0) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走6这个位置
        if((p1.x = curPoint.x - 1) >=0 && (p1.y=curPoint.y-2)>=0) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走7这个位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走0这个位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走1这个位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走2这个位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走3这个位置
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走4这个位置
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }

        return ps;
    }


    public static void sort(List<Point> points) {
        points.sort((e1,e2) -> {
            return next(e1).size() - next(e2).size();
        });
    }



}
