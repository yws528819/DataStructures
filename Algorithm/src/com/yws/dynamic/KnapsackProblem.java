package com.yws.dynamic;

public class KnapsackProblem {

    public static void main(String[] args) {

        int[] w = new int[] {1, 4, 3};//物品的重量
        int[] val = new int[] {1500, 3000, 2000};//物品的价值


        int pCnt = 4;//背包的数量


        int[][] v = new int[w.length + 1][pCnt + 1];
        int path[][] = new int[w.length + 1][pCnt + 1];

        for (int i=1; i<w.length+1; i++) {
            for (int j=1; j<pCnt+1; j++) {
                //物品的重量大于背包的容量，直接用上一个的
                if (w[i-1] > j) {
                    v[i][j] = v[i-1][j];
                }else {
                    //当前的物品价值 + 剩余可装的容量的价值
                    int tempV = val[i-1] + v[i-1][j-w[i-1]];
                    if (v[i-1][j] < tempV) {
                        //这里其实是每次物品的装入，path记录这里就行
                        v[i][j] = tempV;
                        path[i][j] = 1;
                    }else {
                        v[i][j] = v[i-1][j];
                    }
                }

                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }


        //遍历path, 这样输出会把所有的放入情况都得到, 其实我们只需要最后的放入
		//for(int i = 0; i < path.length; i++) {
		//	for(int j=0; j < path[i].length; j++) {
		//		if(path[i][j] == 1) {
		//			System.out.printf("第%d个商品放入到背包\n", i);
		//		}
		//	}
		//}

        //动脑筋
        //int i = path.length - 1; //行的最大下标
        //int j = path[0].length - 1;  //列的最大下标
        //while(i > 0 && j > 0 ) { //从path的最后开始找
        //    if(path[i][j] == 1) {
        //        System.out.printf("第%d个商品放入到背包\n", i);
        //        j -= w[i-1]; //w[i-1]
        //    }
        //    i--;
        //}


    }

}
