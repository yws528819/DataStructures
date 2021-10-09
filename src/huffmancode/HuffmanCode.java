package huffmancode;

import java.util.*;
import java.util.stream.Collectors;

public class HuffmanCode {

    private static Map<Byte, String> huffmanCodeMap = new HashMap<>();
    private static StringBuilder stringBuilder = new StringBuilder();


    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] bytes = huffmanZip(content.getBytes());
        System.out.println(Arrays.toString(bytes));
    }

    public static byte[] huffmanZip(byte[] bytes) {
        //将字符串转成结点对象集合，Node(字符，字符出现次数)
        List<Node> nodes = getNodes(bytes);

        //结点对象构建赫夫曼树
        Node huffmanRoot = createHuffmanTree(nodes);

        //生成对应的赫夫曼树编码映射map
        Map<Byte, String> huffmanCodes = getCodes(huffmanRoot);

        //将原始字符数组转成对应的二进制字符串，将把二进制字符串转成10进制字节数组
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);


        return huffmanCodeBytes;
    }

    /**
     * 根据赫夫曼编码，压缩文本（转成对应的二进制编码，再每8位转成10进制字节，放入返回的数组）
     * @param bytes
     * @param huffmanCodes
     * @return
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        StringBuilder stringBuilder = new StringBuilder();

        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }

        //数组长度可以一句话搞定
        //byte[] retBytes = new byte[(stringBuilder.length() + 7) / 8];
        int len = 0;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        }else {
            len = stringBuilder.length() / 8 + 1;
        }
        //返回的字节数组
        byte[] retBytes = new byte[len];
        int index = 0;
        //每8位（1字节）转成1个10进制数
        for (int i=0; i<stringBuilder.length(); i+=8) {
            String strByte;
            if (i+8 > stringBuilder.length()) {
                strByte = stringBuilder.substring(i);
            }else {
                strByte = stringBuilder.substring(i, i+8);
            }
            //转成十进制数组
            retBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index ++;
        }

        return retBytes;
    }

    /**
     * 获得赫夫曼编码
     * @param node
     * @return
     */
    private static Map<Byte, String> getCodes(Node node) {
        if (node != null) {
            getCodes(node.getLeftNode(), "0", stringBuilder);
            getCodes(node.getRightNode(), "1", stringBuilder);
        }

        return huffmanCodeMap;
    }

    /**
     * 获得赫夫曼编码
     * @param node 结点
     * @param code 左结点-0，右结点-1
     * @param sbd 已经拼接的字符串
     * @return
     */
    private static void getCodes(Node node, String code, StringBuilder sbd) {
        StringBuilder stringBuilder = new StringBuilder(sbd);
        stringBuilder.append(code);
        if (node != null) {
            if (node.getData() == null) {
                //分别向左、向右递归处理
                getCodes(node.getLeftNode(), "0", stringBuilder);
                getCodes(node.getRightNode(), "1", stringBuilder);
            }else {
                huffmanCodeMap.put(node.getData(), stringBuilder.toString());
            }
        }
    }

    /**
     * 构建赫夫曼树
     * @param nodes
     * @return
     */
    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            nodes = sort(nodes);

            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node node = new Node(leftNode, rightNode);

            nodes.add(node);
            nodes.remove(leftNode);
            nodes.remove(rightNode);
        }

        return nodes.get(0);
    }

    /**
     * 字节数组转成结点对象集合，Node(字符，字符出现次数)
     * @param bytes
     * @return
     */
    private static List<Node> getNodes(byte[] bytes) {
        Map<Byte, Integer> map = new HashMap<>();

        for (byte b : bytes) {
            if (map.containsKey(b)) {
                Integer count = map.get(b);
                map.put(b, ++count);
            }else {
                map.put(b, 1);
            }
        }

        List<Node> nodes = new ArrayList<>();
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }

        return nodes;
    }

    /**
     * 集合排序
     * @param nodes
     * @return
     */
    private static List<Node> sort(List<Node> nodes) {
        if (nodes != null) {
            return nodes.stream().sorted((e1,e2) -> {
                return e1.getWeight() - e2.getWeight();
            }).collect(Collectors.toList());
        }
        return null;
    }

}


class Node{
    private Byte data;
    private int weight;
    private Node leftNode;
    private Node rightNode;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    public Node(Node leftNode, Node rightNode) {
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        this.weight = leftNode.getWeight() + rightNode.weight;
    }

    public Byte getData() {
        return data;
    }

    public void setData(Byte data) {
        this.data = data;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
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
                "data=" + data +
                ", weight=" + weight +
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
