package com.yws.kmp;

public class ViolenceMatch {

    public static void main(String[] args) {
        //测试暴力匹配算法
        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String str2 = "尚硅谷你尚硅你~";
        violenceMatch(str1, str2);
    }


    /**
     * 暴力匹配算法
     * @param str
     * @param matchStr
     */
    private static void violenceMatch(String str, String matchStr) {
        char[] allChars = str.toCharArray();
        char[] matchChars = matchStr.toCharArray();

        int i = 0;
        int j = 0;
        while (i < allChars.length && j < matchChars.length) {
            //如果字符一样，两个指针同时往前移动
            if (allChars[i] == matchChars[j]) {
                i ++;
                j ++;
            }else {
                //如果不一样，匹配的字符从头开始算j=0，总字符回退j个字符的下一个字符，比如原来已经2个字符相同，第3个字符不同，就回退2个字符的下一个字符
                i = i - j + 1;
                j = 0;
            }
        }

        //如果匹配到了，j长度就会满足退出条件
        if (j == matchChars.length) {
            System.out.println("匹配到相同字符从第" + (i - j) + "开始");
        }else {
            System.out.println("-1");
        }

    }

}
