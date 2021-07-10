package dfsnbfs;

import java.io.*;

public class Prob5639_이진검색트리2 {
    static Node root;
    static StringBuilder sb = new StringBuilder();

    static class Node {
        int num;
        Node left, right;

        Node(int num) {
            this.num = num;
            this.left = null;
            this.right = null;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        root = new Node(Integer.parseInt(br.readLine()));

        String input;
        while ((input = br.readLine()) != null && input.length() > 0 && !input.equals(""))
            addNode(root, new Node(Integer.parseInt(input)));
        postOrder(root);

        System.out.print(sb);
    }

    public static void addNode(Node root, Node newNode) {
        if (root.num > newNode.num) {
            if (root.left == null)
                root.left = newNode;
            else addNode(root.left, newNode);
        } else {
            if (root.right == null)
                root.right = newNode;
            else addNode(root.right, newNode);
        }
    }

    public static void postOrder(Node root) {
        if (root.left != null) postOrder(root.left);
        if (root.right != null) postOrder(root.right);
        sb.append(root.num).append('\n');
    }
}
