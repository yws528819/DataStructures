package avl;

public class AVLTreeDemo {
    public static void main(String[] args) {
        int[] arr = { 10, 11, 7, 6, 8, 9 };
        //创建一个 AVLTree对象
        AVLTree avlTree = new AVLTree();
        //添加结点
        for(int i=0; i < arr.length; i++) {
            avlTree.addNode(new Node(arr[i]));
        }

        //遍历
        System.out.println("中序遍历");
        avlTree.midOrder();

        System.out.println("在平衡处理~~");
        System.out.println("树的高度=" + avlTree.getRoot().height()); //3
        System.out.println("树的左子树高度=" + avlTree.getRoot().getLeft().height()); // 2
        System.out.println("树的右子树高度=" + avlTree.getRoot().getRight().height()); // 2
        System.out.println("当前的根结点=" + avlTree.getRoot());//8
    }
}


class AVLTree {
    private Node root;

    public AVLTree() {

    }

    public AVLTree(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    /**
     * 添加结点
     * @param node
     */
    public void addNode(Node node) {
        if (root == null) {
            root = node;
        }else {
            root.addNode(node);
        }

        if (root != null && root.getLeft() != null && root.getRight() != null) {
            //根结点的右子树高度>根结点的左子树高度，相差大于1
            if (root.getRight().height() - root.getLeft().height() > 1) {
                //根结点右子树   左子树高度 > 右子树高度，需要进行一次根结点的右结点的【右旋转】
                if (root.getRight().getRight().height() < root.getRight().getLeft().height()) {
                    root.getRight().rightRotate();
                }
                //根结点【左旋转】
                root.leftRotate();
                return;
            }

            /**
             *                   10
             *                  / \
             *                 7   11
             *                / \
             *               6   8
             *                    \
             *                     9
             *
             *     补充：如果9，是挂着6下面的比如是5，就不会有问题
             */

            //根结点的左子树高度>根结点的右子树高度，相差大于1
            if (root.getLeft().height() - root.getRight().height() > 1) {
                //根结点左子树   右子树高度 > 左子树高度，需要进行一次根结点的左结点的【左旋转】
                if (root.getLeft().getLeft().height() < root.getLeft().getRight().height()) {
                    root.getLeft().leftRotate();
                }
                //根结点【右旋转】
                root.rightRotate();
                return;
            }
        }

    }


    /**
     * 遍历
     */
    public void midOrder() {
        if (root == null) {
            System.out.println("树没有结点");
            return;
        }else {
            root.midOrder();
        }
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
     * 添加结点
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
     * 统计树的高度
     * @return
     */
    public int height() {
        return Math.max(this.left == null ? 0 : this.left.height(), this.right == null ? 0 : this.right.height()) + 1;
    }

    /**
     * 左旋转
     *         4                            6
     *        / \                           /\
     *       3   6                         4  7
     *           /\                        /\  \
     *          5  7                      3  5  8
     *              \
     *               8
     */
    public void leftRotate() {
        //创建新的结点（当前结点）
        Node newNode = new Node(val);
        //新结点的左结点是当前结点的左结点
        newNode.left = left;
        //新结点的右结点是当前结点的右结点的右结点
        newNode.right = right.left;

        //当前结点的值变成右结点的值
        val = right.val;
        //当前结点的右结点为当前结点的右结点的右结点
        right = right.right;
        //当前结点的左结点为上门的新结点
        left = newNode;
    }

    /**
     * 右旋转
     *
     *           7                     5
     *           /\                   / \
     *          5  8                 4   7
     *          /\                  / \   \
     *         4  6                3   6   8
     *        /
     *       3
     *
     */
    public void rightRotate() {
        Node newNode = new Node(val);
        newNode.right = right;
        newNode.left = left.right;

        val = left.val;
        left = left.left;
        right = newNode;
    }

}
