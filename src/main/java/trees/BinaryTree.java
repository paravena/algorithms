package trees;

public class BinaryTree<T extends Comparable> {
    private TreeNode root;

    public boolean add(T element) {
        if (root == null) {
            root = new TreeNode(element);
            return true;
        } else {
            return root.add(element);
        }
    }

    public TreeNode find(T element) {
        TreeNode current = root;
        while (current != null) {
            if (current.element.equals(element)) {
                return current;
            } else if (current.element.compareTo(element) < 0) {
                current = current.right;
            } else  {
                current = current.left;
            }
        }
        return current;
    }

    private class TreeNode {
        T element;
        TreeNode parent;
        TreeNode left;
        TreeNode right;

        TreeNode(T element) {
            this.element = element;
        }

        boolean add(T element) {
            if (this.element.equals(element)) {
                return false;
            } else if (this.element.compareTo(element) > 0) {
                if (this.left == null) {
                    this.left = new TreeNode(element);
                    return true;
                } else {
                    this.left.add(element);
                }
            } else if (this.element.compareTo(element) < 0) {
                if (this.right == null) {
                    this.right = new TreeNode(element);
                    return true;
                } else {
                    this.right.add(element);
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {

    }
}
