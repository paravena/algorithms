package btrees;

import util.MathUtility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BTreeValidator {
    public static boolean validates(BTreeNode node) {
        return validates(node, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static boolean validates(BTreeNode node, Integer minLimit, Integer maxLimit) {
        List<Integer> keys = node.keys;
        for (int i = 0; i < keys.size() - 1; i++) {
            if (keys.get(i) > keys.get(i + 1)
                    || !MathUtility.isBetween(keys.get(i), minLimit, maxLimit)
                    || !MathUtility.isBetween(keys.get(i + 1), minLimit, maxLimit)) {
                return false;
            }
        }
        if (node.children != null) {
            List<BTreeNode> children = node.children;
            if (keys.size() >= children.size()) return false;
            Integer min = minLimit;
            Integer max = keys.get(0);
            for (int i = 0; i <= keys.size(); i++) {
                BTreeNode child = children.get(i);
                if (i > 0 && i < keys.size()) {
                    min = keys.get(i - 1);
                    max = keys.get(i);
                } else if (i == keys.size()) {
                    min = keys.get(i - 1);
                    max = maxLimit;
                }
                if (!validates(child, min, max)) return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        BTreeNode node1 = new BTreeNode();
        node1.keys = Arrays.asList(13);

        BTreeNode node2 = new BTreeNode();
        node2.keys = Arrays.asList(4, 7);

        BTreeNode node3 = new BTreeNode();
        node3.keys = Arrays.asList(17, 24);

        node1.children = Arrays.asList(node2, node3);

        BTreeNode node4 = new BTreeNode();
        node4.keys = Arrays.asList(1, 3);

        BTreeNode node5 = new BTreeNode();
        node5.keys = Arrays.asList(5, 6);

        BTreeNode node6 = new BTreeNode();
        node6.keys = Arrays.asList(8, 10, 11);

        node2.children = Arrays.asList(node4, node5, node6);

        BTreeNode node7 = new BTreeNode();
        node7.keys = Arrays.asList(14, 16);

        BTreeNode node8 = new BTreeNode();
        node8.keys = Arrays.asList(18, 20);

        BTreeNode node9 = new BTreeNode();
        node9.keys = Arrays.asList(25, 30, 31, 35);

        node3.children = Arrays.asList(node7, node8, node9);

        boolean validates = BTreeValidator.validates(node1);
        System.out.println("validates = " + validates);
    }
}
