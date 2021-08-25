package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {

    public static void main(String[] args) {

        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";

        List<String> list = getListString(suffixExpression);

        int value = calculate(list);

        System.out.println(value);

    }

    private static int calculate(List<String> list) {
        Stack<String> stack = new Stack();

        for (String item : list) {
            if (item.matches("\\d+")) {
                stack.push(item);
            }else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                }else if (item.equals("-")) {
                    res = num1 - num2;
                }else if (item.equals("*")) {
                    res = num1 * num2;
                }else if (item.equals("/")) {
                    res = num1 / num2;
                }

                stack.push(res + "");
            }
        }
        return Integer.parseInt(stack.pop());
    }

    /**
     * 将逆波兰表达式，拆分放入到集合中
     * @param suffixExpression
     * @return
     */
    private static List<String> getListString(String suffixExpression) {
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();

        for (String s : split) {
            list.add(s);
        }
        return list;
    }


}
