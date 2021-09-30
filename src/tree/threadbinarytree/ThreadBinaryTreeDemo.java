package tree.threadbinarytree;

public class ThreadBinaryTreeDemo {
    public static void main(String[] args) {
        //测试一把中序线索二叉树的功能
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");

        //二叉树，后面我们要递归创建, 现在简单处理使用手动创建
        root.setLeftNode(node2);
        root.setRightNode(node3);
        node2.setLeftNode(node4);
        node2.setRightNode(node5);
        node3.setLeftNode(node6);

        ThreadBinaryTree threadBinaryTree = new ThreadBinaryTree(root);
        threadBinaryTree.threadNodes();

        System.out.println("10号的前驱节点：" + node5.getLeftNode() + ",后继节点：" + node5.getRightNode() );

        System.out.println("线索化遍历===>");
        threadBinaryTree.threadList();
    }
}



class ThreadBinaryTree{
    private HeroNode root;

    private HeroNode pre;


    public ThreadBinaryTree(HeroNode root) {
        this.root = root;
    }

    public void threadNodes() {
        threadNodes(root);
    }

    public void threadNodes(HeroNode node) {
        if(node == null) {
            return;
        }
        //线索化左子树
        threadNodes(node.getLeftNode());

        //pre节点是node节点的上一个节点
        if (node.getLeftNode() == null) {
            node.setLeftNode(pre);
            node.setLeftType(1);
        }

        //每一次递归其实只有pre和当前node，所以只能pre后继当前
        //要么node是叶子节点，要么是pre是叶子节点
        if (pre != null && pre.getRightNode() == null) {
            pre.setRightNode(node);
            pre.setRightType(1);
        }

        pre = node;

        //线索化右子树
        threadNodes(node.getRightNode());

    }

    /**
     * 线索二叉树遍历
     * 理解：1.打印线索化的节点就行
     *      2.顺序：打印线索化左节点->线索化右节点
     */
    public void threadList() {
        HeroNode node = root;

        while (node != null) {
            while (node.getLeftType() == 0) {
                node = node.getLeftNode();
            }

            System.out.println(node);

            while (node.getRightType() == 1) {
                node = node.getRightNode();
                System.out.println(node);
            }

            node = node.getRightNode();
        }

    }






    public HeroNode getRoot() {
        return root;
    }

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public HeroNode getPre() {
        return pre;
    }

    public void setPre(HeroNode pre) {
        this.pre = pre;
    }
}



class HeroNode {
    private int no;
    private String name;
    private HeroNode leftNode;
    private HeroNode rightNode;
    //正常节点-0 前驱后继节点-1
    private int leftType;
    private int rightType;

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

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
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


    public HeroNode preOrderSearch(int no) {
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


    public HeroNode midOrderSearch(int no) {
        HeroNode res = null;
        if (this.getLeftNode() != null) {
            res = this.getLeftNode().midOrderSearch(no);
        }

        if (res != null) {
            return res;
        }

        if (this.getNo() == no) {
            return this;
        }

        if (this.getRightNode() != null) {
            res = this.getRightNode().midOrderSearch(no);
        }

        return res;
    }

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

        if (this.getNo() == no) {
            return this;
        }

        return res;
    }
}
