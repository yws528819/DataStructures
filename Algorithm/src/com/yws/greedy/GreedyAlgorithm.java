package com.yws.greedy;

import java.util.*;

/**
 * 贪心算法
 */
public class GreedyAlgorithm {
    public static void main(String[] args) {
        Map<String, Set<String>> map = new HashMap<>();
        //将各个电台放入到broadcasts
        HashSet<String> hashSet1 = new HashSet<String>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<String>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<String>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");


        HashSet<String> hashSet4 = new HashSet<String>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<String>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        //加入到map
        map.put("K1", hashSet1);
        map.put("K2", hashSet2);
        map.put("K3", hashSet3);
        map.put("K4", hashSet4);
        map.put("K5", hashSet5);

        //所有的地区
        Set<String> allArea = new HashSet<>();
        for(Map.Entry<String, Set<String>> entry : map.entrySet()) {
            Set<String> areas = entry.getValue();
            for (String area : areas) {
                allArea.add(area);
            }
        }


        //每一次for遍历完之后的最大key及最大的交集
        String maxKey = null;
        Set<String> maxKeySameSet = new HashSet<>();

        Set<String> tempSet = new HashSet<>();
        //最后被选中的结果key集（简单理解：这几个key对应的value拼在一起就是所有的value集了）
        List<String> selectKeys = new ArrayList<>();

        while (!allArea.isEmpty()) {

            maxKey = null;

            for(Map.Entry<String, Set<String>> entry : map.entrySet()) {
                //此次遍历的value集（地区）
                tempSet.clear();
                tempSet.addAll(entry.getValue());
                //此次地区与剩余所有地区的交集
                tempSet.retainAll(allArea);

                //与此次for循环里当前最大的交集比，如果比他大，当前记为最大交集
                if (maxKey == null || (tempSet.size() > 0 && tempSet.size() > maxKeySameSet.size())) {
                    maxKey = entry.getKey();
                    maxKeySameSet.clear();
                    maxKeySameSet.addAll(tempSet);
                }
            }
            //循环完得到这一轮的最大交集key和最大交集地区集
            if (maxKey != null) {
                //加入结果集，去除交集，就是下次循环的总地区
                selectKeys.add(maxKey);
                allArea.removeAll(maxKeySameSet);
            }
        }

        System.out.println("得到的选择结果是" + selectKeys);
    }


}
