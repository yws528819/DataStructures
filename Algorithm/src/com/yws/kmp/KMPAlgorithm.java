package com.yws.kmp;

import java.util.Arrays;

/**
 * KMP查找算法
 */
public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        //String str2 = "BBC";

        int[] next = getKmpNext("ABCDABD"); //[0, 0, 0, 0, 1, 2, 0]
        System.out.println("next=" + Arrays.toString(next));

        int index = kmpSearch(str1, str2, next);
        System.out.println("index=" + index); // 15了
    }


    /**
     * KMP查找（被匹配的字符串可以从头到尾查找，不用回退，由匹配的字符回退代替，通过部分匹配表实现）
     * @param str
     * @param matchStr
     * @param next
     * @return
     */
    public static int kmpSearch(String str, String matchStr, int[] next) {

        int j = 0;

        for (int i = 0; i<str.length(); i++) {

            while (j > 0 && str.charAt(i) != matchStr.charAt(j)) {
                j = next[j - 1];
            }

            if (str.charAt(i) == matchStr.charAt(j)) {
                j ++;
            }

            if (j == matchStr.length()) {
                return i - j + 1;
            }
        }

        return -1;
    }


    /**
     * 获得匹配字符的部分匹配表
     * @param matchStr
     * @return
     */
    public static int[] getKmpNext(String matchStr) {
        int[] next = new int[matchStr.length()];
        int j = 0;
        next[j] = 0;

        for(int i=1; i<matchStr.length(); i++) {
            while (j > 0 && matchStr.charAt(i) != matchStr.charAt(j)) {
                j = next[j - 1];
            }

            if (matchStr.charAt(i) == matchStr.charAt(j)) {
                j ++;
            }
            next[i] = j;
        }

        return next;
    }

}
