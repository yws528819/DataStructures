package queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {

    public static void main(String[] args) throws Exception {
        CircleArrayQueue queue = new CircleArrayQueue(4);
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


class CircleArrayQueue{

    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    public CircleArrayQueue(int arrayMaxSize) {
        maxSize = arrayMaxSize;
        front = 0;
        rear = 0;
        arr = new int[arrayMaxSize];
    }




    public void add(int i) throws Exception {
        if (full()) {
            System.out.println("队列满了");
            return;
        }

        arr[rear] = i;
        rear = (rear + 1) % maxSize;
    }

    public int get() throws Exception {
        if (empty()) {
            throw new Exception("队列空的");
        }

        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }


    public void list() {
        for (int i=front; i<front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    private int size() {
        return (rear + maxSize - front) % maxSize;
    }


    public int head() throws Exception {
        if (empty()) {
            throw new Exception("队列空的");
        }
        return arr[front];
    }


    private boolean empty() {
        if (rear == front) {
            return true;
        }
        return false;
    }


    private boolean full() {
        if ((rear + 1) % maxSize == front) {
            return true;
        }
        return false;
    }

}
