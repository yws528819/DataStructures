package linkedlist;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //创建节点
        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode heroNode3 = new HeroNode(3, "吴用", "智多星");
        HeroNode heroNode4= new HeroNode(4, "林冲", "豹子头");
        //加入节点
        // singleLinkedList.add(heroNode3);
        // singleLinkedList.add(heroNode4);
        // singleLinkedList.add(heroNode1);
        // singleLinkedList.add(heroNode2);
        //加入节点并且实现排序
        singleLinkedList.addByOrder(heroNode3);
        singleLinkedList.addByOrder(heroNode4);
        singleLinkedList.addByOrder(heroNode1);
        singleLinkedList.addByOrder(heroNode2);


        //修改节点
        HeroNode h2 = new HeroNode(2, "小卢", "玉麒麟~~~");
        singleLinkedList.update(2, h2);

        //删除节点
        singleLinkedList.delete(2);
        singleLinkedList.delete(3);

        //展示节点
        singleLinkedList.list();

    }
}


class HeroNode{
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }


    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}


class SingleLinkedList {
    private HeroNode head = new HeroNode(0, "", "");//头节点

    //找到最后一个节点，在这个节点上添加下一个节点为参数节点
    public void add(HeroNode heroNode) {
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;

    }

    public void addByOrder(HeroNode heroNode) {

        HeroNode temp = head;
        boolean flag = false;
        while(true) {
            //空链表直接添加
            if (temp.next == null) {
                temp.next = heroNode;
                break;
            }

            if (heroNode.no == temp.next.no) {//说明希望添加的heroNode的编号已然存在
                flag = true;
                break;
            }

            if (heroNode.no > temp.next.no) {
                heroNode.next = temp.next;
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
    public void update(int no, HeroNode newNode) {

        //判断是否为空链表
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        HeroNode temp = head.next;


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
        HeroNode temp = head;

        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }

            if (temp.next.no == no) {
                temp.next = temp.next.next;
                flag = true;
                break;
            }

            temp = temp.next;
        }

        if (!flag) {
            System.out.printf("要删除的节点 %d 不存在", no);
        }
    }

    public void list() {
        //判断链表是否为空
        if(head.next == null) {
            System.out.println("链表为空");
            return;
        }

        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                return;
            }

            System.out.println(temp);

            temp = temp.next;

        }
    }
}


