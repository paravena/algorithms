package trees;

public class TreeNode<T extends Comparable<T>> {
    T element;
    TreeNode<T> parent;
    TreeNode<T> left;
    TreeNode<T> right;
    TreeNodeVisitor visitor;

    TreeNode(T element) {
        this(element, null);
    }

    TreeNode(T element, TreeNodeVisitor visitor) {
        this.element = element;
        this.visitor = visitor;
    }

    boolean add(T element) {
        if (this.element.compareTo(element) >= 0) {
            if (this.left == null) {
                this.left = new TreeNode<T>(element);
                return true;
            } else {
                this.left.add(element);
            }
        } else if (this.element.compareTo(element) < 0) {
            if (this.right == null) {
                this.right = new TreeNode<T>(element);
                return true;
            } else {
                this.right.add(element);
            }
        }
        return false;
    }

    void visit() {
        if (visitor != null) {
            visitor.visit(element);
        } else {
            System.out.println(element);
        }
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
