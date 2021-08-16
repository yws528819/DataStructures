package linkedlist;

public class Josephu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        //几个小孩组成环形链表
        circleSingleLinkedList.addBoy(5);

        circleSingleLinkedList.showBoy();
        //设置小孩每隔几个出圈
        circleSingleLinkedList.countBoy(1, 2, 5);
    }

}

class CircleSingleLinkedList{
    private Boy first = null;

    /**
     * 构造一个环形链表
     * @param nums
     */
    public void addBoy(int nums) {
        //参数校验
        if (nums < 1) {
            System.out.println("nums值不正确");
        }

        Boy cur = null;
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                cur = first;
            }
            cur.setNext(boy);
            if (i == nums) {
                boy.setNext(first);
            }

            cur = cur.getNext();
        }
    }

    /**
     * 显示链表元素
     */
    public void showBoy() {
        if (first == null) {
            System.out.println("没有任何小孩~~");
            return;
        }

        Boy cur = first;
        while (cur.getNext() != first) {
            System.out.printf("小孩的编号 %d \n", cur.getNo());
            cur = cur.getNext();
        }
        //显示最后一个元素
        System.out.printf("小孩的编号 %d \n", cur.getNo());
    }


    /**
     * 根据用户的输入，计算小孩的出圈顺序
     * @param startNo 从第几个小孩开始
     * @param countNum 数几下
     * @param nums 最初的小孩总数
     */
    public void countBoy(int startNo, int countNum, int nums) {
        if (first == null || startNo < 1 || countNum < 1 || startNo > nums) {
            System.out.println("参数输入有误，请重新输入");
            return;
        }

        //1.找到辅助节点初始的位置，辅助节点在头节点的前一个节点（单向的，所以只能从头节点开始遍历）
        Boy helper = first;
        while (helper.getNext() != first) {
            helper = helper.getNext();
        }

        //2.头节点和辅助节点分别移动到参数起始的startNo位置， first->startNo， help->startNo-1
        for (int i = 1; i <= startNo-1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }

        //3.遍历出圈小孩，跟第2点类似，只是多了节点移出去，helper和first下个节点重新赋值
        while (helper != first) {
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.printf("出圈的小孩：%d \n", first.getNo());
            //辅助节点的下个节点设为first节点的下一个
            helper.setNext(first.getNext());
            //first节点重新赋值
            first = helper.getNext();
        }

        System.out.printf("最后留在圈中的小孩 %d \n", first.getNo());
    }

}



class Boy{
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}




