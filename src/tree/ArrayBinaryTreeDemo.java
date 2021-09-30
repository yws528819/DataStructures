package tree;

public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6,7};

        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        arrayBinaryTree.preOrder();
    }


}

class ArrayBinaryTree{
    private int[] arr;

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder() {
        preOrder(0);
    }

    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按二叉树的前序遍历");
            return;
        }
        //输出当前元素
        System.out.println(arr[index]);

        //当前元素的左子节点对应的下标递归遍历
        //第n个元素的左子节点为  2 * n + 1
        int left = 2 * index + 1;
        if (left < arr.length) {
            preOrder(left);
        }
        //当前元素的右子节点对应的下标递归遍历
        //第n个元素的右子节点为  2 * n + 2
        int right = 2 * index + 2;
        if (right < arr.length) {
            preOrder(right);
        }
    }

}
