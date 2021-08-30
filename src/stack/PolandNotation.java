package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {

    public static void main(String[] args) {

        String expression = "1+((2+3)*4)-5";
        //将中缀表达式转成对应的list（拆分字符串，区分是符号还是多位数字）
        List<String> infiExpressionList = toInfiExpressionList(expression);
        //将中缀表达式集合转成后缀表达式（逆波兰表达式）集合
        List<String> suffixExpressionList =  transferToSuffixExpression(infiExpressionList);
        System.out.println(suffixExpressionList);

        //String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";
        //将中缀表达式字符串（带空格，用空格拆分）转成list
        //List<String> list = getListString(suffixExpression);

        //计算后缀表达式集合
        int value = calculate(suffixExpressionList);
        System.out.println(value);

    }

    /**
     * 将中缀表达式集合转成后缀表达式（逆波兰表达式）集合
     * @param infiExpressionList
     * @return
     */
    private static List<String> transferToSuffixExpression(List<String> infiExpressionList) {
        //符号栈
        Stack<String> operStack = new Stack<>();
        List<String> result = new ArrayList<>();
        String oper = null;
        /**
         * 参数：1个中缀表达式集合
         * 处理使用：1个结果集合，1个栈（符号栈）
         * 处理过程：遍历中缀表达式集合的元素
         * 1) 如果是数字，直接放入集合里
         * 2) 如果是左括号(，直接放入符号栈里
         * 3) 如果是右括号)，将符号栈里的符号一一出栈放入集合里，直到左括号弹出
         * 4) 如果是计算符号，就比较栈里符号优先度是否比当前元素符号高或者相等，
         *      如果是，栈弹出，放入集合里，
         *      再比较栈后面的符号，重复操作，直到栈里的优先度比元素符号的小
         *      如果小，就将当前元素符号入符号栈
         * 5) 遍历完，如果符号栈还有元素，一一出栈，放入结果集合里
         *
         */
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
                    //第一个符号，符号栈里是空的
                    if (operStack.isEmpty()) {
                        oper = item;
                    }else {
                        while (operStack.size() > 0 && Operation.getValue(item) <= Operation.getValue(oper = operStack.peek())) {
                            operStack.pop();
                            result.add(oper);
                        }
                    }
                    operStack.push(item);
                }
            }

        }

        while (!operStack.isEmpty()) {
            result.add(operStack.pop());
        }

        return result;
    }


    /**
     * 将中缀表达式转成对应的list（拆分字符串，区分是符号还是多位数字）
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

    /**
     * 计算后缀表达式集合
     * @param list
     * @return
     */
    private static int calculate(List<String> list) {
        Stack<String> stack = new Stack();

        /**
         * 将集合（符号或者数字）一个个元素，放到一个栈里
         * 如果是数字，直接入栈
         * 如果是符号，就出栈2个数字，进行该符号的计算，将计算结果入栈（后缀表达式已经解决好计算的顺序）
         */
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
