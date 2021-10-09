package huffmancode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 赫夫曼编码
 */
public class HuffmanCode {

    public static Map<Byte, String> huffmanCodeMap = new HashMap<>();
    public static StringBuilder stringBuilder = new StringBuilder();


    public static void main(String[] args) {

        String content = "i like like like java do you like a java !!!!!!!";
        //赫夫曼压缩处理
        byte[] bytes = huffmanZip(content.getBytes());
        System.out.println(Arrays.toString(bytes));

        //赫夫曼解压处理
        Byte[] srcBytes = decode(bytes, huffmanCodeMap);
        byte[] srcb = new byte[srcBytes.length];
        int i=0;
        for(Byte b : srcBytes) {
            srcb[i] = (byte) b;
            i++;
        }
        System.out.println(new String(srcb));
    }


    /**
     * 赫夫曼字节数组根据赫夫曼编码，还原成原字节数组
     * @param huffmanBytes 赫夫曼字节数组
     * @param huffmanCodes 赫夫曼编码
     * @return
     */
    public static Byte[] decode(byte[] huffmanBytes, Map<Byte, String> huffmanCodes) {
        //压缩的字节数组，还原成二进制字符串
        String huffmanStr = bytesToBitString(huffmanBytes);

        //赫夫曼编码的键值对调
        Map<String, Byte> map = new HashMap<>();
        for(Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        StringBuilder sbd = new StringBuilder();
        List<Byte> list = new ArrayList();
        for(int i=0; i<huffmanStr.length(); i++) {
            sbd.append(huffmanStr.substring(i, i + 1));
            Byte aByte = map.get(sbd.toString());
            if (aByte != null) {
                list.add(aByte);
                sbd = new StringBuilder();
            }
        }

        return list.toArray(new Byte[list.size()]);
    }

    /**
     * 压缩的字节数组，还原成二进制字符串
     * @param huffmanBytes
     * @return
     */
    public static String bytesToBitString(byte[] huffmanBytes) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i< huffmanBytes.length-2; i++) {
            int b = huffmanBytes[i] | 256;
            String s = Integer.toBinaryString(b);
            sb.append(s.substring(s.length() - 8));
        }
        //最后一个字节，代表最后倒数第二个字节（真正意义的最后一个字节）的长度
        int lastByteLength = huffmanBytes[huffmanBytes.length - 1];
        String lastByte = Integer.toBinaryString(huffmanBytes[huffmanBytes.length-2]);
        for (int i=0; i<(lastByteLength-lastByte.length()); i++ ) {
            lastByte = "0" + lastByte;
        }
        sb.append(lastByte);
        return sb.toString();
    }


    /**
     * 赫夫曼压缩处理
     * @param bytes
     * @return
     */
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
        byte[] retBytes = new byte[len+1];
        int index = 0;
        int lastByteLen = 0;
        //每8位（1字节）转成1个10进制数
        for (int i=0; i<stringBuilder.length(); i+=8) {
            String strByte;
            if (i+8 > stringBuilder.length()) {
                strByte = stringBuilder.substring(i);
                //最后剩余的一个字节，记录下长度
                lastByteLen = (byte) (stringBuilder.length() - i);
            }else {
                strByte = stringBuilder.substring(i, i+8);
            }
            //转成十进制数组
            retBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index ++;
        }

        retBytes[index] = (byte) lastByteLen;

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
