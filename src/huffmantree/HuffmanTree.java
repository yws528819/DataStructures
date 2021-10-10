package huffmantree;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 排序->前2个组成新子树加入->移除前2个->排序...->直到移除只剩1个结点
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int arr[] = { 13, 7, 8, 3, 29, 6, 1 };

        HuffmanTree huffmanTree = new HuffmanTree();
        Node huffmanNode = huffmanTree.createHuffmanTree(arr);

        huffmanTree.preOrder(huffmanNode);
    }


    private Node createHuffmanTree(int[] arr) {
        List<Node> nodes = new ArrayList<>();
        for (int num : arr) {
            nodes.add(new Node(num));
        }

        while(nodes.size() > 1) {
            //升序排序
            nodes = sort(nodes);

            //合并前2个最小的结点
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node node = new Node(leftNode, rightNode);
            //加入合并的父节点，移除这2个子结点
            nodes.add(node);
            nodes.remove(leftNode);
            nodes.remove(rightNode);
        }

        return nodes.get(0);
    }

    /**
     * 结点集合的升序排序
     * @param nodes
     * @return
     */
    private List<Node> sort(List<Node> nodes) {
        if (nodes != null) {
            return nodes.stream().sorted((e1,e2) -> {
                return e1.getValue() - e2.getValue();
            }).collect(Collectors.toList());
        }
        return null;
    }

    /**
     * 前序遍历
     * @param node
     */
    private void preOrder(Node node) {
        if (node == null) {
            System.out.println("空树不能遍历");
        }else {
            node.preOrder();
        }

    }

}


class Node{
    private int value;
    private Node leftNode;
    private Node rightNode;


    public Node(int value) {
        this.value = value;
    }

    public Node(Node leftNode, Node rightNode) {
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        this.value = leftNode.getValue() + rightNode.getValue();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }


    /**
     * 前序排序
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
}
