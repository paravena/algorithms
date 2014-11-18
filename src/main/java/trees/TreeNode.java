package trees;

public class TreeNode<T extends Comparable<T>,V> {
    protected T key;
    protected V payload;
    protected TreeNode<T, V> parent;
    protected TreeNode<T, V> left;
    protected TreeNode<T, V> right;
    protected TreeNodeVisitor<T> visitor;

    public TreeNode(T key) {
        this(key, null);
    }

    public TreeNode(T key, TreeNodeVisitor<T> visitor) {
        this.key = key;
        this.visitor = visitor;
    }

    public boolean add(T key, V payload) {
        if (this.key.compareTo(key) >= 0) {
            if (this.left == null) {
                this.left = new TreeNode<T, V>(key);
                return true;
            } else {
                this.left.add(key, payload);
            }
        } else if (this.key.compareTo(key) < 0) {
            if (this.right == null) {
                this.right = new TreeNode<T, V>(key);
                return true;
            } else {
                this.right.add(key, payload);
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
        } else if (hasLeftChild()) {
            // connect parent of current node with left child
            if (this.parent != null) {
                TreeNode leftChild = this.left;
                this.getParent().setLeft(leftChild);
                leftChild.setParent(this.getParent());
            } else {

            }
        } else if (hasRightChild()) {
            // connect parent of current node with right child
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

    public V getPayload() {
        return payload;
    }

    public void setPayload(V payload) {
        this.payload = payload;
    }
}
