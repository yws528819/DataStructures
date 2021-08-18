package stack;

import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) {
        //先创建一个栈
        ArrayStack stack = new ArrayStack(4);
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;

        while(loop) {
            System.out.println("======================================");
            System.out.println("show: 表示显示栈");
            System.out.println("exit: 退出程序");
            System.out.println("push: 表示添加数据到栈(入栈)");
            System.out.println("pop: 表示从栈取出数据(出栈)");
            System.out.println("请输入你的选择");
            String key = scanner.next();
            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数字");
                    int val = scanner.nextInt();
                    stack.push(val);
                    break;
                case "pop":
                    try{
                        System.out.printf("出栈的数据是%d\n", stack.pop());
                    }catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~~~");
    }
}

class ArrayStack{
    private int top = -1;
    private int[] stack;
    private int maxSize;

    public ArrayStack(int maxSize) {
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

}
