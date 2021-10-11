package binarysorttree;

/**
 * 二叉排序树
 */
public class BinarySortTreeDemo {

    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i=0; i<arr.length; i++) {
            binarySortTree.addNode(new Node(arr[i]));
        }

        //binarySortTree.midOrder();

        System.out.println("查找结点值：");
        System.out.println(binarySortTree.searchNode(7));
        System.out.println("查找父结点值：");
        System.out.println(binarySortTree.searchParentNode(2));

        //System.out.println("删除结点：");
        //binarySortTree.deleteNode(1);
        //binarySortTree.deleteNode(2);
        //binarySortTree.deleteNode(3);
        //binarySortTree.deleteNode(5);
        //binarySortTree.deleteNode(7);
        //binarySortTree.deleteNode(9);
        //binarySortTree.deleteNode(10);
        //binarySortTree.deleteNode(12);
        binarySortTree.midOrder();

    }



}

class BinarySortTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    /**
     * 添加节点
     * @param node
     */
    public void addNode(Node node) {
        if (root == null) {
            root = node;
            return;
        }
        root.addNode(node);
    }

    /**
     * 中序遍历
     */
    public void midOrder() {
        if (root == null) {
            return;
        }
        root.midOrder();
    }

    /**
     * 查找结点
     * @param val
     * @return
     */
    public Node searchNode(int val) {
        return root.searchNode(val);
    }

    /**
     * 查找结点对应的父结点
     * @param val
     * @return
     */
    public Node searchParentNode(int val) {
        if (root.getVal() == val) {
            return null;
        }else {
            return root.searchParentNode(val);
        }
    }

    /**
     * 删除结点
     * @param val
     */
    public void deleteNode(int val) {
        //删除的节点
        Node node = searchNode(val);
        if (node == null) {
            System.out.println("找不到对应的结点");
            return;
        }

        //判断删除的节点场景
        if (node.getLeft() == null && node.getRight() == null) {
            //1.是叶子结点
            Node pNode = searchParentNode(val);
            //1.1刚好是根节点
            if (pNode == null) {
                root = null;
            }else {
            //1.2判断是父结点的左子结点，还是右子结点
                if (pNode.getLeft() != null &&  pNode.getLeft().getVal() == val) {
                    pNode.setLeft(null);
                }
                if (pNode.getRight() != null && pNode.getRight().getVal() == val) {
                    pNode.setRight(null);
                }
            }

        }else if (node.getLeft() != null && node.getRight() != null) {
            //2.节点含有左右子树
            //删除右子树里最小的结点，返回最小结点值
            int minVal = delRightTreeMin(node);
            //将删除结点的值替换为删除的最小结点值
            node.setVal(minVal);
        }else {
            //3.结点只有左子树或者右子树
            Node pNode = searchParentNode(val);
            //3.1删除的结点只有左子树
            if (node.getLeft() != null) {
                //①删除的结点有父结点
                if (pNode != null) {
                    if (pNode.getLeft() != null && pNode.getLeft().getVal() == node.getVal()) {
                        //父结点的左结点是删除的结点，给父结点的左结点设值
                        pNode.setLeft(node.getLeft());
                    }else if (pNode.getRight() != null && pNode.getRight().getVal() == node.getVal()) {
                        //父结点的右结点是删除的结点，给父结点的右结点设值
                        pNode.setRight(node.getLeft());
                    }
                //②删除的结点没有父结点（是根结点）
                }else {
                    root = node.getLeft();
                }
            //3.2删除的结点只有右子树
            }else if (node.getRight() != null) {
                //①删除的结点有父结点
                if (pNode != null) {
                    if (pNode.getLeft() != null && pNode.getLeft().getVal() == node.getVal()) {
                        //父结点的左结点是删除的结点，给父结点的左结点设值
                        pNode.setLeft(node.getRight());
                    }else if (pNode.getRight() != null && pNode.getRight().getVal() == node.getVal()) {
                        //父结点的右结点是删除的结点，给父结点的右结点设值
                        pNode.setRight(node.getRight());
                    }
                //②删除的结点没有父结点（是根结点）
                }else {
                    root = node.getRight();
                }
            }
        }

    }

    /**
     * 删除当前结点下的，最左结点（最左结点就是当前子树里最小的，该方法其实就是删除右子树里最小的结点，返回最小结点值）
     * @param node
     * @return
     */
    public int delRightTreeMin(Node node) {
        Node cur = node;
        while (cur.getLeft() != null) {
            cur = cur.getLeft();
        }
        deleteNode(cur.getVal());
        return cur.getVal();
    }
}


class Node {
    private int val;
    private Node left;
    private Node right;

    public Node(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                '}';
    }

    /**
     * 添加节点
     * @param node
     */
    public void addNode(Node node) {
        if (this.left == null && this.val > node.getVal()) {
            this.left = node;
            return;
        }
        if (this.right == null && this.val <= node.getVal()) {
            this.right = node;
            return;
        }
        if (this.val > node.getVal()) {
            this.left.addNode(node);
            return;
        }

        if (this.val <= node.getVal()) {
            this.right.addNode(node);
            return;
        }

    }

    /**
     * 中序遍历
     */
    public void midOrder() {
        if (this.getLeft() != null) {
            this.getLeft().midOrder();
        }
        System.out.println(this);
        if (this.getRight() != null) {
            this.getRight().midOrder();
        }
    }

    /**
     * 寻找节点
     * @param val
     * @return
     */
    public Node searchNode(int val) {
        //当前节点->左子结点->右子结点分别递归比较
        if (this.val == val) {
            return this;
        }
        if (val < this.val && this.getLeft() != null) {
            return this.getLeft().searchNode(val);
        }
        if (val >= this.val && this.getRight() != null) {
            return this.getRight().searchNode(val);
        }
        return null;
    }

    /**
     * 寻找节点对应的父节点
     * @param val
     * @return
     */
    public Node searchParentNode(int val) {
        //当前节点->左子结点->右子结点分别递归比较
        if (this.getLeft() != null && this.getLeft().getVal() == val) {
            return this;
        }

        if (this.getRight() != null && this.getRight().getVal() == val) {
            return this;
        }
        if (val < this.val && this.getLeft() != null) {
            return this.getLeft().searchParentNode(val);
        }
        if (val >= this.val && this.getRight() != null) {
            return this.getRight().searchParentNode(val);
        }
        return null;
    }

}
