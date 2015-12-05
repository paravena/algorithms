package trees;


import java.io.InputStream;
import java.util.*;

public class SwapNodes {
    public static void main(String[] args) {
        SwapNodes sn = new SwapNodes();
        sn.readInput(SwapNodes.class.getClassLoader().getResourceAsStream("swap_nodes_input_04.txt"));
    }

    private void readInput() {
        readInput(System.in);
    }


    private void readInput(InputStream in) {
        Scanner scan = new Scanner(in);
        int N = scan.nextInt();
        scan.nextLine();
        Queue<Node> queue = new LinkedList<Node>();
        Node root = new Node(1);
        queue.add(root);
        int i = 0;
        while (i < N) {
            Node current = queue.remove();
            String[] tokens = scan.nextLine().split("\\s");

            if (!"-1".equals(tokens[0])) {
                current.left = new Node(Integer.valueOf(tokens[0]));
                queue.add(current.left);
            }

            if (!"-1".equals(tokens[1])) {
                current.right = new Node(Integer.valueOf(tokens[1]));
                queue.add(current.right);
            }
            i++;
        }
        int H = calculateHeight(root);
        int T = scan.nextInt();
        scan.nextLine();
        i = 0;
        while (i < T) {
            int h = scan.nextInt();
            swapNodesAt(root, h, H);
            i++;
            if (scan.hasNext()) scan.nextLine();
        }
    }

    private int calculateHeight(Node root) {
        if (root == null) return 0;
        return 1 + Math.max(calculateHeight(root.left), calculateHeight(root.right));
    }

    private void swapNodesAt(Node root, int h, int limit) {
        int i = 1;
        int k = h;
        while (h <= limit) {
            List<Node> nodesAtHeight = findNodesAtHeight(root, h);
            for (Node n : nodesAtHeight) {
                swapNode(n);
            }
            i++;
            h = k * i;
        }
        inorder(root);
        System.out.print("\n");
    }

    private void inorder(Node root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    private void swapNode(Node n) {
        Node tmp = n.left;
        n.left = n.right;
        n.right = tmp;
    }

    private List<Node> findNodesAtHeight(Node root, int h) {
        List<Node> result = new ArrayList<Node>();
        findNodesAtHeight(root, h, result);
        return result;
    }

    private void findNodesAtHeight(Node root, int h, List<Node> result) {
        if (root == null) return;
        if (h == 1) {
            result.add(root);
        } else {
            findNodesAtHeight(root.left, h - 1, result);
            findNodesAtHeight(root.right, h - 1, result);
        }
    }

    private class Node {
        protected int data;
        protected Node left;
        protected Node right;

        public Node(int data) {
            this.data = data;
        }
    }
}

