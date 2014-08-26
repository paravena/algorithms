package trees;

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
        System.out.println("post order");
        tree.postorder();
    }
}
