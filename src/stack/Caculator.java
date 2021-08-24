package stack;

public class Caculator {
    public static void main(String[] args) throws Exception {
        //表达式
        String expression = "7*2+1-2/2+2/2*3";
        //数字栈
        ArrayStack2 numStack = new ArrayStack2(10);
        //符号栈
        ArrayStack2 operStack = new ArrayStack2(10);

        //遍历表达式，每个字符遍历处理
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            //如果是符号
            if (operStack.isOper(ch)) {
                //第一个符号，直接入栈
                if (operStack.isEmpty()) {
                    operStack.push(ch);
                }else {
                    char peek = (char) operStack.peek();
                    //符号栈里的栈顶元素优先级较高或者一样，就计算
                    if (operStack.priority(peek) >= operStack.priority(ch)) {
                        //符号栈顶出栈
                        operStack.pop();
                        //连续2次出栈数字栈元素，进行计算
                        int num1 = numStack.pop();
                        int num2 = numStack.pop();
                        int res = numStack.cal(num1, num2, peek);
                        numStack.push(res);
                        //符号字符入栈
                        operStack.push(ch);
                    }else {
                        //符号字符入栈
                        operStack.push(ch);
                    }
                }

            }else {
                //数字
                if (numStack.isEmpty()) {
                    //数字栈是空的，直接入栈
                    numStack.push(Integer.parseInt(ch+""));
                }else {
                    if (!operStack.isOper(expression.charAt(i - 1))) {
                        //如果上一个字符也是数字就拼接字符再入栈
                        String chs = numStack.pop() + "" + ch;
                        numStack.push(Integer.parseInt(chs));
                    }else {
                        //上一个字符是符号直接入栈
                        numStack.push(Integer.parseInt(ch+""));
                    }
                }

            }
        }

        //遍历完，符号栈还有符号，应遍历计算完
        if (!operStack.isEmpty()) {
            //栈倒置下
            ArrayStack2 rOperStack = operStack.reverse();
            ArrayStack2 rNumStack = numStack.reverse();

            while (!rOperStack.isEmpty()) {
                //符号栈顶出栈
                char operate = (char) rOperStack.pop();
                //连续2次出栈数字栈元素，进行计算
                int num1 = rNumStack.pop();
                int num2 = rNumStack.pop();
                int res = rNumStack.cal(num2, num1, operate);
                rNumStack.push(res);
            }
            System.out.printf("表达式 %s = %d \n", expression, rNumStack.pop());
        }


    }



}




class ArrayStack2{
    private int top = -1;
    private int[] stack;
    private int maxSize;

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }


    /**
     * 判断栈是否是空的
     * @return
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 判断栈是否是满的
     * @return
     */
    public boolean isFull() {
        return top == maxSize -1;
    }

    /**
     * 元素入栈
     * @param data
     */
    public void push(int data) {
        if (!isFull()) {
            stack[++top] = data;
        }else {
            System.out.println("栈满了");
        }
    }

    /**
     * 元素出栈
     */
    public int pop() throws Exception{
        if (!isEmpty()) {
            return stack[top--];
        } else {
            throw new Exception("栈空了");
        }
    }

    /**
     * 查看出栈元素
     * @return
     */
    public int peek() {
        return stack[top];
    }


    /**
     * 遍历栈
     */
    public void list() {
        if (isEmpty()) {
            System.out.println("栈是空的");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    /**
     * 判断是否是符号
     * @param val
     * @return
     */
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    /**
     * 符号优先级判断，优先级越高返回的数字越高
     * @param val
     * @return
     */
    public int priority(char val) {
        if (val == '*' || val == '/') {
            return 1;
        }else if (val == '+' || val == '-' ) {
            return 0;
        }else {
            return -1;//目前只有+ - * /
        }
    }

    /**
     * 根据符号计算
     * @param num1 第一个出栈
     * @param num2 第二个出栈
     * @param peek
     */
    public int cal(int num1, int num2, char peek) {
        if (peek == '+') {
            return num1 + num2;
        }else if (peek == '-') {
            return num2 - num1;
        }else if (peek == '*') {
            return num1 * num2;
        }else if (peek == '/') {
            return num2 / num1;
        }else {
            return 0;
        }
    }

    /**
     * 栈逆向转成新栈，栈底变栈顶
     * @return
     */
    public ArrayStack2 reverse() throws Exception {
        ArrayStack2 stack2 = new ArrayStack2(maxSize);

        while(!isEmpty()) {
            stack2.push(pop());
        }

        return stack2;
    }
}
