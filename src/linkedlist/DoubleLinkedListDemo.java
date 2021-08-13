package linkedlist;

import java.util.Stack;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        DoubleLinkedList singleLinkedList = new DoubleLinkedList();
        //创建节点
        HeroNode2 HeroNode1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 HeroNode2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 HeroNode3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 HeroNode4= new HeroNode2(4, "林冲", "豹子头");
        //加入节点
        // singleLinkedList.add(HeroNode3);
        // singleLinkedList.add(HeroNode4);
        // singleLinkedList.add(HeroNode1);
        // singleLinkedList.add(HeroNode2);
        //加入节点并且实现排序
        singleLinkedList.addByOrder(HeroNode3);
        singleLinkedList.addByOrder(HeroNode4);
        singleLinkedList.addByOrder(HeroNode1);
        singleLinkedList.addByOrder(HeroNode2);

        //修改节点
        HeroNode2 h2 = new HeroNode2(2, "小卢", "玉麒麟~~~");
        singleLinkedList.update(2, h2);

        //删除节点
        singleLinkedList.delete(4);
        singleLinkedList.delete(2);

        //展示节点
        singleLinkedList.list();

        //链表的长度
        System.out.println("链表的长度：" + singleLinkedList.count());
    }

}


class HeroNode2{
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next;
    public HeroNode2 pre;


    public HeroNode2(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}


class DoubleLinkedList {
    private HeroNode2 head = new HeroNode2(0, "", "");//头节点

    public HeroNode2 getHead() {
        return head;
    }

    //找到最后一个节点，在这个节点上添加下一个节点为参数节点
    public void add(HeroNode2 heroNode) {
        HeroNode2 temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    /**
     * 排序插入
     * @param heroNode
     */
    public void addByOrder(HeroNode2 heroNode) {
        HeroNode2 temp = head;
        boolean flag = false;
        while(true) {
            //空链表直接添加
            if (temp.next == null) {
                temp.next = heroNode;
                heroNode.pre = temp;
                break;
            }

            if (heroNode.no == temp.next.no) {//说明希望添加的heroNode的编号已然存在
                flag = true;
                break;
            }

            if (heroNode.no > temp.next.no) {
                heroNode.next = temp.next;
                heroNode.pre = temp;

                temp.next.pre = heroNode;
                temp.next = heroNode;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            System.out.printf("准备插入的英雄编号 %d 已存在", heroNode.no);
        }

    }


    //遍历节点，找到对应的节点，更新
    public void update(int no, HeroNode2 newNode) {

        //判断是否为空链表
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        HeroNode2 temp = head.next;


        boolean flag = false;//是否找到标志

        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == no) {
                temp.name = newNode.name;
                temp.nickName = newNode.nickName;
                flag = true;
                break;
            }

            temp = temp.next;
        }

        if (!flag) {
            System.out.printf("没有找到编号 %d 的节点，不能修改\n", newNode.no);
        }
    }

    //遍历节点，找到对应节点的上一个节点，将上一个节点的下个节点指向到下下个节点
    public void delete(int no) {
        HeroNode2 temp = head.next;

        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }

            if (temp.no == no) {
                temp.pre.next = temp.next;
                if (temp.next != null) {
                    temp.next.pre = temp.pre;
                }
                flag = true;
                break;
            }

            temp = temp.next;
        }

        if (!flag) {
            System.out.printf("要删除的节点 %d 不存在 \n", no);
        }
    }

    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }

        HeroNode2 temp = head.next;

        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    /**
     * 返回链表长度
     *
     * @return
     */
    public int count() {
        if (head.next == null) {
            return 0;
        }
        HeroNode2 curNode = head.next;
        int i = 0;
        while (curNode != null) {
            i++;
            curNode = curNode.next;
        }
        return i;
    }

}
