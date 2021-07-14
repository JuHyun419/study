package tree;

class Node {
    int data;
    Node left;
    Node right;

    public Node(int data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}

public class Tree {
    public Node root;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Node makeNode(Node left, int data, Node right) {
        return new Node(data, left, right);
    }

    /*
    - 전위 순위(preorder) 순회
        - 루트 노드를 먼저 순회한 후 '왼쪽 하위 -> 오른쪽 하위' 순으로 순회
    - 중위 순위(inorder) 순회
        - 왼쪽 최 하위 노드를 먼저 순회한 후 '상위 노드 -> 오른쪽 하위' 순으로 순회
    - 후위 순위(postorder) 순회
        - 왼쪽 최 하위 노드를 먼저 순회한 후 '오른쪽 하위 노드 -> 상위 노드' 순으로 순회
     */


    /**
     * 전위 순회
     *  - 루트 노드를 먼저 순회한 후 '왼쪽 하위 -> 오른쪽 하위' 순으로 순회
     * @param node
     */
    public void preorder(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.data + " ");
        preorder(node.left);
        preorder(node.right);
    }


    /**
     * 중위 순회
     *  - 왼쪽 최 하위 노드를 먼저 순회한 후 '상위 노드 -> 오른쪽 하위' 순으로 순회
     * @param node
     */
    public void inorder(Node node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        System.out.print(node.data + " ");
        inorder(node.right);
    }


    /**
     * 후위 순회
     * - 왼쪽 최하위 노드를 먼저 순회한 후 '오른쪽 하위 노드 -> 상위 노드' 순으로 순회
     * @param node
     */
    public void postorder(Node node) {
        if (node == null) {
            return;
        }
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.data + " ");
    }

}
