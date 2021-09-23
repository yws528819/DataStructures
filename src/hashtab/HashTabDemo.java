package hashtab;

import java.util.Scanner;

public class HashTabDemo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmpHashTable empHashTable = new EmpHashTable(8);

        while (true) {
            System.out.println("============================");
            System.out.println("add-添加");
            System.out.println("list-查询");
            System.out.println("del-删除");
            System.out.println("exit-退出");
            System.out.println("请输入：");
            String input = scanner.next();

            switch (input){
                case "add":
                    System.out.println("请输入编号：");
                    int no = scanner.nextInt();
                    System.out.println("请输入名字：");
                    String name = scanner.next();
                    Emp emp = new Emp(no, name);
                    empHashTable.add(emp);
                    break;
                case "list":
                    empHashTable.list();
                    break;
                case "del":
                    System.out.println("输入删除的编号：");
                    int delNo = scanner.nextInt();
                    empHashTable.delete(delNo);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }



    }


}

class EmpHashTable {

    private int size;
    private EmpLinkedList[] empLinkedLists;

    public EmpHashTable(int size) {
        empLinkedLists = new EmpLinkedList[size];
        this.size = size;
        for (int i = 0; i < size; i++) {
            empLinkedLists[i] = new EmpLinkedList();
        }
    }


    public EmpLinkedList getEmpLinkedList(int id) {
        return empLinkedLists[id % size];
    }


    public void add(Emp emp) {
        getEmpLinkedList(emp.getId()).add(emp);
    }

    public void list() {
        for (int i=0; i<size; i++) {
            System.out.printf("数组%d链表：", i+1);
            empLinkedLists[i].list();
            System.out.println();
        }
    }

    public void delete(int id) {
        getEmpLinkedList(id).delete(id);
    }

}



class EmpLinkedList{
    private Emp head;


    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }

        Emp cur = head;
        while(cur.getNext() != null) {
            cur = cur.getNext();
        }

        cur.setNext(emp);
    }

    public void list() {
        Emp cur = head;
        while(cur != null) {
            System.out.print("=>" + cur.getId() + "号员工");
            cur = cur.getNext();
        }
    }

    public void delete(int id) {
        Emp cur = head;
        boolean delFlag = false;

        if (head.getId() == id) {
            if (head.getNext() != null) {
                head = head.getNext();
                return;
            }else {
                head = null;
                return;
            }
        }

        while (cur.getNext() != null) {
            if (cur.getNext().getId() == id) {
                cur.setNext(cur.getNext().getNext());
                delFlag = true;
                break;
            }
        }
        if (!delFlag) {
            System.out.printf("找不到%d号员工", id);
            System.out.println();
        }
    }

}


class Emp {
    private int id;
    private String name;
    private Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Emp getNext() {
        return next;
    }

    public void setNext(Emp next) {
        this.next = next;
    }
}
