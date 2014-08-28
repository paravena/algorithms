package trees;

public class TreeNode<T extends Comparable<T>> {
    protected T element;
    protected TreeNode<T> parent;
    protected TreeNode<T> left;
    protected TreeNode<T> right;
    protected TreeNodeVisitor<T> visitor;

    public TreeNode(T element) {
        this(element, null);
    }

    public TreeNode(T element, TreeNodeVisitor<T> visitor) {
        this.element = element;
        this.visitor = visitor;
    }

    public boolean add(T element) {
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

    public void visit() {
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

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public TreeNode<T> getParent() {
        return parent;
    }

    public void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }
}
