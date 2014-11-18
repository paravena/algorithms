package trees;

import basic.Queue;

public class BinarySearchTree<T extends Comparable<T>, V> {
    private int size = 0;
    private TreeNode<T, V> root;

    public boolean add(T key, V payload) {
        boolean result;
        if (root == null) {
            root = new TreeNode<T, V>(key);
            result = true;
        } else {
            result = root.add(key, payload);
        }
        size++;
        return result;
    }

    public void inOrder() {
        root.inOrder();
    }

    public void postOrder() {
        root.postOrder();
    }

    public void levelOrder() {
        Queue<TreeNode<T, V>> queue = new Queue<TreeNode<T, V>>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            TreeNode<T, V> n = queue.dequeue();
            n.visit();
            if (n.getLeft() != null) queue.enqueue(n.getLeft());
            if (n.getRight() != null) queue.enqueue(n.getRight());
        }
    }

    public int height() {
        return height(root);
    }

    private int height(TreeNode<T, V> node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
    }

    public TreeNode<T, V> find(T key) {
        TreeNode<T, V> current = root;
        while (current != null) {
            if (current.key.compareTo(key) < 0) {
                current = current.right;
            } else if (current.key.compareTo(key) >= 0)  {
                current = current.left;
            } else {
                return current;
            }
        }
        return null;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer, Integer> tree = new BinarySearchTree<Integer, Integer>();
        /*
               18
              /  \
            12   25
           /  \    \
         4    15   28
        /    /  \    \
      1    13   17   29
       \    \
        3   14
         */
        tree.add(18, 18);
        tree.add(12, 12);
        tree.add(25, 25);
        tree.add(4, 4);
        tree.add(15, 15);
        tree.add(1, 1);
        tree.add(3, 3);
        tree.add(13, 13);
        tree.add(14, 14);
        tree.add(17, 17);
        //tree.add(25);
        tree.add(28, 28);
        tree.add(29, 29);
        System.out.println("\ninOrder order traversal");
        tree.inOrder();
        System.out.println("\npost order traversal");
        tree.postOrder();
        System.out.println("\norder lever traversal");
        tree.levelOrder();
        boolean binarySearchTree = BinaryTreeUtilities.isBinarySearchTree(tree.root);
        System.out.println("\nbinarySearchTree = " + binarySearchTree);
        System.out.println("tree.height: " + tree.height());
    }
}
