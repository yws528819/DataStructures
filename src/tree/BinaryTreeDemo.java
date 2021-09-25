package tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();

        HeroNode rootNode = new HeroNode(1, "宋江");
        HeroNode heroNode1 = new HeroNode(2, "吴用");
        HeroNode heroNode2 = new HeroNode(3, "卢俊义");
        HeroNode heroNode3 = new HeroNode(4, "林冲");

        binaryTree.setRoot(rootNode);
        rootNode.setLeftNode(heroNode1);
        rootNode.setRightNode(heroNode2);
        heroNode2.setRightNode(heroNode3);

        System.out.println("前序遍历：");
        binaryTree.preOrder();
        System.out.println("中序遍历：");
        binaryTree.midOrder();
        System.out.println("后序遍历：");
        binaryTree.postOrder();


    }

}

class BinaryTree{
    private HeroNode root;

    public HeroNode getRoot() {
        return root;
    }

    public void setRoot(HeroNode root) {
        this.root = root;
    }


    public void preOrder() {
        if (root != null)
            root.preOrder();
    }

    public void midOrder() {
        if (root != null) {
            root.midOrder();
        }
    }

    public void postOrder() {
        if (root != null) {
            root.postOrder();
        }
    }

}




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
