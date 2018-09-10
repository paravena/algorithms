package challenges.trees;

import basic.Queue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BinarySearchTree<T extends Comparable<T>, V> {
    private int size = 0;
    private TreeNode<T, V> root;

    public boolean put(T key, V payload) {
        boolean result;
        if (root == null) {
            root = new TreeNode<T, V>(key);
            result = true;
        } else {
            result = root.put(key, payload);
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

    public V get(T key) {
        V result = null;
        TreeNode<T, V> found = root.get(key);
        if (found != null) result = found.getValue();
        return result;
    }

    public TreeNode<T, V> getTreeNode(T key) {
        return root.get(key);
    }

    public void printKDistanceNodesFromRoot(int k) {
        TreeNode<T, V> currentNode = root;
        List<TreeNode<T, V>> listOfTreeNodes = new ArrayList<TreeNode<T, V>>();
        printKDistanceNodes(currentNode, listOfTreeNodes, k);
        printListOfTreeNodes(listOfTreeNodes);
    }

    private void printKDistanceNodes(TreeNode<T, V> currentNode, List<TreeNode<T, V>> listOfNodes, int k) {
        if (currentNode == null) return;
        if (k == 0) {
            listOfNodes.add(currentNode);
        } else if (k > 0) {
            printKDistanceNodes(currentNode.left, listOfNodes, k - 1);
            printKDistanceNodes(currentNode.right, listOfNodes, k - 1);
        }
    }

    private void printListOfTreeNodes(List<TreeNode<T, V>> listOfTreeNodes) {
        System.out.print("[");
        for (TreeNode<T, V> treeNode : listOfTreeNodes) {
            System.out.print(treeNode.key + ",");
        }
        System.out.println("]");
    }

    public void printTopView() {
        Set<Integer> distancesFromRoot = new HashSet<Integer>();
        List<TreeNode<T, V>> topTreeNodeList = new ArrayList<TreeNode<T, V>>();
        Queue<TreeNode<T, V>> queue = new Queue<TreeNode<T, V>>();
        root.setDistance(0);
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            TreeNode<T, V> n = queue.dequeue();
            if (!distancesFromRoot.contains(n.distance)) {
                topTreeNodeList.add(n);
                distancesFromRoot.add(n.distance);
            }
            if (n.getLeft() != null) {
                n.left.setDistance(n.getDistance() - 1);
                queue.enqueue(n.getLeft());
            }
            if (n.getRight() != null) {
                n.right.setDistance(n.getDistance() + 1);
                queue.enqueue(n.getRight());
            }
        }
        printListOfTreeNodes(topTreeNodeList);
    }

    public boolean isSubTree(TreeNode<T, V> treeNode) {
        TreeNode<T, V> subTreeNode = root.get(treeNode.key);
        return subTreeNode != null && subTreeNode.isIdentical(treeNode);
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<Integer, Integer>();
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
        Integer[] numbers = {18, 12, 25, 4, 15, 1, 3, 13, 14, 17, 28, 29};
        for (Integer number : numbers) {
            bst.put(number, number);
        }
        System.out.println("\ninOrder order traversal");
        bst.inOrder();
        System.out.println("\npost order traversal");
        bst.postOrder();
        System.out.println("\norder lever traversal");
        bst.levelOrder();
        boolean binarySearchTree = BinaryTreeUtilities.isBinarySearchTree(bst.root);
        System.out.println("\nbinarySearchTree = " + binarySearchTree);
        System.out.println("tree.height: " + bst.height());
        bst.printKDistanceNodesFromRoot(1);
        bst.printTopView();
        System.out.println("is subtree " + bst.isSubTree(bst.getTreeNode(15)));
        Integer[] otherNumbers = {15, 13, 17, 14};
        BinarySearchTree<Integer, Integer> otherBst = new BinarySearchTree<Integer, Integer>();
        for (Integer otherNumber : otherNumbers) {
            otherBst.put(otherNumber, otherNumber);
        }
        System.out.println("is subtree " + bst.isSubTree(otherBst.root));
    }
}
