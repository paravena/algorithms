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

    public boolean put(T key, V value) {
        if (this.key.compareTo(key) >= 0) {
            if (this.left == null) {
                this.left = new TreeNode<T, V>(key, value);
                return true;
            } else {
                this.left.put(key, value);
            }
        } else if (this.key.compareTo(key) < 0) {
            if (this.right == null) {
                this.right = new TreeNode<T, V>(key);
                return true;
            } else {
                this.right.put(key, value);
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

    public TreeNode<T, V> findMin() {
        TreeNode<T, V> current = this;
        while (current.hasLeftChild()) {
            current = current.left;
        }
        return current;
    }

    /**
     * Three cases:
     * If the node has a right child, then the successor is the smallest key in the right subtree.
     * If the node has no right child and is the left child of its parent, then the parent is the successor.
     * If the node is the right child of its parent, and itself has no right child, then the successor to this
     * node is the successor of its parent, excluding this node.

     * @return successor tree node
     */
    public TreeNode<T, V> findSuccessor() {
        TreeNode<T, V> result = null;
        if (this.hasRightChild()) {
            result = this.findMin();
        } else {
            // TODO this part is not clear for me
            if (this.parent != null) { // is not root
                if (this.isLeftChild()) {
                    result = this.parent;
                } else {
                    this.parent.right = null;
                    result = parent.findSuccessor();
                    this.parent.right = this;
                }
            }
        }
        return result;
    }

    public void spliceOut() {
        if (this.isLeaf()) {
            if (this.isLeftChild()) {
                this.parent.left = null;
            } else {
                this.parent.right = null;
            }
        } else if (this.hasAnyChildren()) {
            if (this.hasLeftChild()) {
                if (this.isLeftChild()) {
                    this.parent.left = this.left;
                } else {
                    this.parent.right = this.left;
                }
                this.left.parent = this.parent;
            } else {
                if (this.isLeftChild()) {
                    this.parent.left = this.right;
                } else {
                    this.parent.right = this.right;
                }
                this.right.parent = this.parent;
            }
        }
    }

    public TreeNode<T, V> get(T key) {
        if (this.key.compareTo(key) == 0) {
            return this;
        } else if (key.compareTo(this.key) < 0) {
            return this.left.get(key);
        } else if (key.compareTo(this.key) > 0) {
            return this.right.get(key);
        }
        return null;
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

    public void replaceNodeData(T key, V value, TreeNode<T, V> leftChild, TreeNode<T, V> rightChild) {
        this.key = key;
        this.value = value;
        this.left = leftChild;
        this.right = rightChild;
        if (this.hasLeftChild()) {
            this.left.parent = this;
        }
        if (this.hasRightChild()) {
            this.right.parent = this;
        }
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
            // get a successor depending on where the current node
            // is located either left or right
            TreeNode<T, V> successor = this.findSuccessor();
            successor.spliceOut();
            this.key = successor.key;
            this.value = successor.value;
        } else {
            if (this.hasLeftChild()) {
                if (this.isLeftChild()) {
                    this.left.parent = this.parent;
                    this.parent.left = this.left;
                } else if (this.isRightChild()) {
                    this.left.parent = this.parent;
                    this.parent.right = this.left;
                } else { // is root
                    this.replaceNodeData(this.left.key,
                            this.left.value,
                            this.left.left,
                            this.left.right);
                }
            } else { // has right child
                if (this.isLeftChild()) {
                    this.right.parent = this.parent;
                    this.parent.left = this.right;
                } else if (this.isRightChild()) {
                    this.right.parent = this.parent;
                    this.parent.right = this.right;
                } else { // is root
                    this.replaceNodeData(this.right.key,
                            this.right.value,
                            this.right.left,
                            this.right.right);
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
