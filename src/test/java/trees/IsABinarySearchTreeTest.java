package trees;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class IsABinarySearchTreeTest {
    @Test
    public void correctBSTShouldBeValidated() {
        Node root = initializeValidBynarySearchTree();
        assertTrue("should be a valid binary search tree", IsABinarySearchTree.checkBST(root));
    }

    @Test
    public void incorrectBSTShouldBeValidated() {
        Node root = initializeInvalidBynarySearchTree();
        assertFalse("should be an invalid binary search tree", IsABinarySearchTree.checkBST(root));
    }

    public Node initializeValidBynarySearchTree() {
        /*
               3
             /   \
            2     5
           /     / \
          1     4   6
        */

        Node root = new Node();
        root.data = 3;

        Node left1 = new Node();
        left1.data = 2;
        Node right1 = new Node();
        right1.data = 5;
        root.left = left1;
        root.right = right1;

        Node left2 = new Node();
        left2.data = 1;
        left1.left = left2;

        Node left3 = new Node();
        left3.data = 4;
        Node  right3 = new Node();
        right3.data = 6;

        right1.left = left3;
        right1.right = right3;

        return root;
    }

    public Node initializeInvalidBynarySearchTree() {
        /*
               3
             /   \
            5     2
           / \   /
          1   4 6
        */

        Node root = new Node();
        root.data = 3;

        Node left1 = new Node();
        left1.data = 5;
        Node right1 = new Node();
        right1.data = 2;
        root.left = left1;
        root.right = right1;

        Node left2 = new Node();
        left2.data = 1;
        Node right2 = new Node();
        right2.data = 4;
        left1.left = left2;
        left1.right = right2;

        Node left3 = new Node();
        left3.data = 6;
        right1.left = left3;

        return root;
    }
}
