package trees;

import basic.Queue;

public class BinarySearchTree<T extends Comparable<T>> {
    private TreeNode<T> root;

    public boolean add(T element) {
        if (root == null) {
            root = new TreeNode<T>(element);
            return true;
        } else {
            return root.add(element);
        }
    }

    public void inorder() {
        root.inorder();
    }

    public void postorder() {
        root.postorder();
    }

    public void levelorder() {
        Queue<TreeNode<T>> queue = new Queue<TreeNode<T>>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            TreeNode<T> n = queue.dequeue();
            n.visit();
            if (n.getLeft() != null) queue.enqueue(n.getLeft());
            if (n.getRight() != null) queue.enqueue(n.getRight());
        }
    }

    public int height() {
        return height(root);
    }

    private int height(TreeNode<T> node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
    }

    public T find(T element) {
        TreeNode<T> current = root;
        while (current != null) {
            if (current.element.compareTo(element) < 0) {
                current = current.right;
            } else if (current.element.compareTo(element) >= 0)  {
                current = current.left;
            } else {
                return current.element;
            }
        }
        return null;
    }


    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
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
        tree.add(18);
        tree.add(12);
        tree.add(25);
        tree.add(4);
        tree.add(15);
        tree.add(1);
        tree.add(3);
        tree.add(13);
        tree.add(14);
        tree.add(17);
        //tree.add(25);
        tree.add(28);
        tree.add(29);
        System.out.println("\ninorder order traversal");
        tree.inorder();
        System.out.println("\npost order traversal");
        tree.postorder();
        System.out.println("\norder lever traversal");
        tree.levelorder();
        boolean binarySearchTree = BinaryTreeUtilities.isBinarySearchTree(tree.root);
        System.out.println("\nbinarySearchTree = " + binarySearchTree);
        System.out.println("tree.height: " + tree.height());
    }
}
