package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {

    public static void main(String[] args) {


        String expression = "1+((2+3)*4)-5";
        List<String> infiExpressionList = toInfiExpressionList(expression);

        List<String> suffixExpressionList =  transferToSuffixExpression(infiExpressionList);



        // String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";
        //
        // List<String> list = getListString(suffixExpression);
        //
        // int value = calculate(list);
        //
        // System.out.println(value);

    }

    private static List<String> transferToSuffixExpression(List<String> infiExpressionList) {
        //符号栈
        Stack<String> operStack = new Stack<>();
        List<String> result = new ArrayList<>();
        String oper = null;

        for (int i=0; i<infiExpressionList.size(); i++) {
            String item = infiExpressionList.get(i);
            if (item.matches("\\d+")) {
                result.add(item);
            }else {
                if (item.equals("(")) {
                    operStack.push(item);
                }else if (item.equals(")")) {
                    while (!(oper = operStack.pop()).equals("(")) {
                        result.add(oper);
                    }
                }else {
                    while (operStack.size() > 0 && Operation.getValue(item) <= Operation.getValue(oper = operStack.pop())) {
                        result.add(oper);
                    }
                    operStack.push(oper);
                }
            }

        }

        while (!operStack.isEmpty()) {
            result.add(operStack.pop());
        }

        return result;
    }


    /**
     * 将中缀表达式转成对应的list
     * @param expression
     * @return
     */
    private static List<String> toInfiExpressionList(String expression) {

        int i=0;
        List<String> infiList = new ArrayList<>();

        while(i < expression.length()) {
            char c = expression.charAt(i);
            //如果不是数字
            if(c < 48 || c > 57) {
                //直接放入集合
                infiList.add("" + c);
            }else {
                String num = "" + c;
                //考虑多位数，如果下一个字符还是数字，就拼接
                while (i+1 < expression.length() && expression.charAt(i + 1) >=48 && expression.charAt(i + 1) <= 57) {
                    num += expression.charAt(++i);
                }
                infiList.add(num);
            }
            i++;
        }
        return infiList;
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

class Operation{
    private static final int ADD = 1;
    private static final int SUB = 1;
    private static final int MUTI = 2;
    private static final int DIV = 2;

    public static int getValue(String operate) {
        int res = 0;
        switch (operate) {
            case "+":
                res = ADD;
                break;
            case "-":
                res = SUB;
                break;
            case "*":
                res = MUTI;
                break;
            case "/":
                res = DIV;
                break;
            default:
                System.out.println("不存在运算符:" + operate);
                break;
        }
        return res;
    }

}
