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

    public void inorder() {
        root.inorder();
    }

    public T find(T element) {
        TreeNode current = root;
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

    private class TreeNode {
        T element;
        TreeNode parent;
        TreeNode left;
        TreeNode right;

        TreeNode(T element) {
            this.element = element;
        }

        boolean add(T element) {
            if (this.element.compareTo(element) >= 0) {
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

        void visit() {
            System.out.println(element);
        }

        void inorder() {
            if (left != null) {
                left.inorder();
            }
            this.visit();
            if (right != null) {
                right.inorder();
            }
        }

        void postorder() {
            if (left != null) {
                left.postorder();
            }
            if (right != null) {
                right.postorder();
            }
            this.visit();
        }

    }

    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<Integer>();
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
        tree.add(25);
        tree.add(28);
        tree.add(29);
        tree.inorder();
    }
}
