package tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();

        HeroNode rootNode = new HeroNode(1, "宋江");
        HeroNode heroNode1 = new HeroNode(2, "吴用");
        HeroNode heroNode2 = new HeroNode(3, "卢俊义");
        HeroNode heroNode3 = new HeroNode(4, "林冲");
        HeroNode heroNode4 = new HeroNode(5, "关胜");

        binaryTree.setRoot(rootNode);
        rootNode.setLeftNode(heroNode1);
        rootNode.setRightNode(heroNode2);
        heroNode2.setRightNode(heroNode3);
        heroNode2.setLeftNode(heroNode4);

        // System.out.println("前序遍历：");
        // binaryTree.preOrder();
        // System.out.println("中序遍历：");
        // binaryTree.midOrder();
        // System.out.println("后序遍历：");
        // binaryTree.postOrder();

        // System.out.println("前序遍历查找：");
        // binaryTree.preOrderSearch(5);
        // System.out.println("中序遍历查找：");
        // binaryTree.midOrderSearch(5);
        // System.out.println("后序遍历查找：");
        // binaryTree.postOrderSearch(5);

        System.out.println("删除前，前序遍历：");
        binaryTree.preOrder();
        binaryTree.delNode(5);
        System.out.println("删除后，前序遍历");
        binaryTree.preOrder();
    }

}

//二叉树
class BinaryTree{
    private HeroNode root;

    public HeroNode getRoot() {
        return root;
    }

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //前序遍历（对外，还是由二叉树提供）
    public void preOrder() {
        if (root != null)
            root.preOrder();
    }

    //中序遍历（对外，还是由二叉树提供）
    public void midOrder() {
        if (root != null) {
            root.midOrder();
        }
    }

    //后序遍历（对外，还是由二叉树提供）
    public void postOrder() {
        if (root != null) {
            root.postOrder();
        }
    }

    //前序遍历查找（对外，还是由二叉树提供）
    public void preOrderSearch(int no) {
        if(root == null) {
            return;
        }
        HeroNode heroNode = root.preOrderSearch(no);
        if(heroNode != null) {
            System.out.printf("编号%d为%s", no, heroNode.getName());
        }else {
            System.out.printf("找不到编号%d", no);
        }
        System.out.println();
    }

    //中序遍历查找（对外，还是由二叉树提供）
    public void midOrderSearch(int no) {
        if(root == null) {
            return;
        }
        HeroNode heroNode = root.midOrderSearch(no);
        if(heroNode != null) {
            System.out.printf("编号%d为%s", no, heroNode.getName());
        }else {
            System.out.printf("找不到编号%d", no);
        }
        System.out.println();

    }

    //后序遍历查找（对外，还是由二叉树提供）
    public void postOrderSearch(int no) {
        if(root == null) {
            return;
        }
        HeroNode heroNode = root.postOrderSearch(no);
        if(heroNode != null) {
            System.out.printf("编号%d为%s", no, heroNode.getName());
        }else {
            System.out.printf("找不到编号%d", no);
        }
        System.out.println();
    }

    //删除树节点（对外，还是由二叉树提供）
    public void delNode(int no) {
        if (root != null) {
            //父节点刚好是
            if (root.getNo() == no) {
                root = null;
            }else {
                //递归子节点，只能通过父节点去删除子节点
                root.delNode(no);
            }
        }else {
            System.out.println("空树不能删除");
        }
    }

}



//树节点
class HeroNode{
    private int no;
    private String name;
    private HeroNode leftNode;
    private HeroNode rightNode;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(HeroNode leftNode) {
        this.leftNode = leftNode;
    }

    public HeroNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(HeroNode rightNode) {
        this.rightNode = rightNode;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        System.out.println(this);
        if (this.getLeftNode() != null) {
            this.getLeftNode().preOrder();
        }
        if (this.getRightNode() != null) {
            this.getRightNode().preOrder();
        }
    }

    /**
     * 中序遍历
     */
    public void midOrder() {
        if (this.getLeftNode() != null) {
            this.leftNode.midOrder();
        }
        System.out.println(this);
        if (this.getRightNode() != null) {
            this.getRightNode().midOrder();
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        if (this.getLeftNode() != null) {
            this.getLeftNode().postOrder();
        }
        if (this.getRightNode() != null) {
            this.getRightNode().postOrder();
        }
        System.out.println(this);
    }


    /**
     * 前序遍历查找
     * @param no
     * @return
     */
    public HeroNode preOrderSearch(int no) {
        System.out.println("遍历次数~");
        if (this.getNo() == no) {
            return this;
        }

        HeroNode res = null;
        if (this.getLeftNode() != null) {
            res = this.getLeftNode().preOrderSearch(no);
        }

        if (res != null) {
            return res;
        }

        if (this.getRightNode() != null) {
            res = this.getRightNode().preOrderSearch(no);
        }

        return res;
    }

    /**
     * 中序遍历查找
     * @param no
     * @return
     */
    public HeroNode midOrderSearch(int no) {
        HeroNode res = null;
        if (this.getLeftNode() != null) {
            res = this.getLeftNode().midOrderSearch(no);
        }

        if (res != null) {
            return res;
        }
        System.out.println("遍历次数~");
        if (this.getNo() == no) {
            return this;
        }

        if (this.getRightNode() != null) {
            res = this.getRightNode().midOrderSearch(no);
        }

        return res;
    }

    /**
     * 后序遍历查找
     * @param no
     * @return
     */
    public HeroNode postOrderSearch(int no) {
        HeroNode res = null;
        if (this.getLeftNode() != null) {
            res = this.getLeftNode().postOrderSearch(no);
        }

        if (res != null) {
            return res;
        }

        if (this.getRightNode() != null) {
            res = this.getRightNode().postOrderSearch(no);
        }

        if (res != null) {
            return res;
        }
        System.out.println("遍历次数~");
        if (this.getNo() == no) {
            return this;
        }

        return res;
    }


    /**
     * 删除树节点
     * @param no
     */
    public void delNode(int no) {
        if (this.getLeftNode() != null && this.getLeftNode().getNo() == no) {
            this.setLeftNode(null);
            return;
        }
        if (this.getRightNode() != null && this.getRightNode().getNo() == no) {
            this.setRightNode(null);
            return;
        }

        if (this.getLeftNode() != null) {
            this.getLeftNode().delNode(no);
        }
        if (this.getRightNode() != null) {
            this.getRightNode().delNode(no);
        }
    }
}
