package queue;

import java.util.Scanner;

public class ArrayQueueDemo {

    public static void main(String[] args) throws Exception {
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loopFlag = true;

        while(loopFlag) {
            System.out.println("s(show)：显示队列");
            System.out.println("e(exit)：退出程序");
            System.out.println("a(add)：添加数据到队列");
            System.out.println("g(get)：从队列中取出数据");
            System.out.println("h(head)：查看队列头数据");
            key  = scanner.next().charAt(0);//接收一个字符
            switch (key) {
                case 's':
                    queue.list();
                    break;
                case 'e':
                    scanner.close();
                    loopFlag = false;
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    queue.add(value);
                    break;
                case 'g':
                    int res = queue.get();
                    System.out.printf("取出的数据是%d\n", res);
                    break;
                case 'h':
                    int h = queue.head();
                    System.out.printf("队列头的数据是%d\n", h);
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }

}


class ArrayQueue{

    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    public ArrayQueue(int arrayMaxSize) {
        maxSize = arrayMaxSize;
        front = -1;
        rear = -1;
        arr = new int[arrayMaxSize];
    }




    public void add(int i) throws Exception {
        if (full()) {
            throw new Exception("队列满了");
        }

        rear ++;
        arr[rear] = i;
    }

    public int get() throws Exception {
        if (empty()) {
            throw new Exception("队列空的");
        }

        front++;
        return arr[front];
    }


    public void list() {
        for (int i=front+1; i<rear+1; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }


    public int head() throws Exception {
        if (empty()) {
            throw new Exception("队列空的");
        }
        return arr[front + 1];
    }


    private boolean empty() {
        if (rear == front) {
            return true;
        }
        return false;
    }


    private boolean full() {
        if (rear == maxSize-1) {
            return true;
        }
        return false;
    }

}
