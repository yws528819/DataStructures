package binarysorttree;

public class BinarySortTreeDemo {

    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i=0; i<arr.length; i++) {
            binarySortTree.addNode(new Node(arr[i]));
        }

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

}
