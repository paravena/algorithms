package trees;

public class TreeNode<T extends Comparable<T>,V> {
    protected T key;
    protected V value;
    protected TreeNode<T, V> parent;
    protected TreeNode<T, V> left;
    protected TreeNode<T, V> right;
    protected TreeNodeVisitor<T> visitor;

    public TreeNode(T key) {
        this(key, null, null);
    }

    public TreeNode(T key, V value) {
        this(key, value, null);    
    }

    public TreeNode(T key, TreeNodeVisitor<T> visitor) {
        this(key, null, visitor);    
    }
    
    public TreeNode(T key, V value, TreeNodeVisitor<T> visitor) {
        this.key = key;
        this.visitor = visitor;
    }

    public boolean add(T key, V value) {
        if (this.key.compareTo(key) >= 0) {
            if (this.left == null) {
                this.left = new TreeNode<T, V>(key, value);
                return true;
            } else {
                this.left.add(key, value);
            }
        } else if (this.key.compareTo(key) < 0) {
            if (this.right == null) {
                this.right = new TreeNode<T, V>(key);
                return true;
            } else {
                this.right.add(key, value);
            }
        }
        return false;
    }

    public void visit() {
        if (visitor != null) {
            visitor.visit(key);
        } else {
            System.out.print(key + ",");
        }
    }

    public void inOrder() {
        if (left != null) {
            left.inOrder();
        }
        this.visit();
        if (right != null) {
            right.inOrder();
        }
    }

    public void postOrder() {
        if (left != null) {
            left.postOrder();
        }
        if (right != null) {
            right.postOrder();
        }
        this.visit();
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    public boolean hasRightChild() {
        return right != null;
    }

    public boolean hasLeftChild() {
        return left != null;
    }

    public boolean hasBothChildren() {
        return left != null && right != null;
    }

    public boolean hasAnyChildren() {
        return left != null || right != null;
    }

    public boolean isLeftChild() {
        return parent != null && getParent().left == this;
    }

    public boolean isRightChild() {
        return parent != null && getParent().right == this;
    }

    public boolean isRoot() {
        return parent == null;
    }
    public void replaceNodeData(T element, TreeNode<T, V> leftChild, TreeNode<T, V> rightChild) {

    }

    public boolean remove() {
        // Three cases need to be covered:
        //   1 - The node to be deleted has no children
        //   2 - The node to be deleted has only one child
        //   3 - The node to be deleted has two children
        if (isLeaf()) {
            // just remove it
            if (this == this.getParent().getLeft()) {
                this.getParent().setLeft(null);
            } else {
                this.getParent().setRight(null);
            }
        } else if (hasBothChildren()) {
            // find a successor depending on where the current node
            // is located either left or right
        } else {
            if (hasLeftChild()) {
                if (isLeftChild()) {

                } else if (isRightChild()) {

                } else { // is root

                }
            } else { // has right child
                if (isLeftChild()) {

                } else if (isRightChild()) {

                } else { // is root

                }
            }
        }
        return true;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public TreeNode<T, V> getParent() {
        return parent;
    }

    public void setParent(TreeNode<T, V> parent) {
        this.parent = parent;
    }

    public TreeNode<T, V> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<T, V> left) {
        this.left = left;
    }

    public TreeNode<T, V> getRight() {
        return right;
    }

    public void setRight(TreeNode<T, V> right) {
        this.right = right;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
