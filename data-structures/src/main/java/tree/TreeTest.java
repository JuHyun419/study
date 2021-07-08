package tree;

/*
        (1)
       ⬃  ⬂
     (2)   (3)
    ⬃  ⬂
  (4)  (5)

  Inorder   (Left, Root, Right): 4 2 5 1 3
  Preorder  (Root, Left, Right): 1 2 4 5 3
  Postorder (Left, Right, Root): 4 5 2 3 1
 */

public class TreeTest {
    public static void main(String[] args) {
        Tree tree = new Tree();
        Node n4 = tree.makeNode(null, 4, null);
        Node n5 = tree.makeNode(null, 5, null);

        Node n2 = tree.makeNode(n4, 2, n5);
        Node n3 = tree.makeNode(null, 3, null);
        Node n1 = tree.makeNode(n2, 1, n3);
        tree.setRoot(n1);

        // 4 2 5 1 3
        tree.inorder(tree.getRoot());
        System.out.println();

        // 1 2 4 5 3
        tree.preorder(tree.getRoot());
        System.out.println();

        // 4 5 2 3 1
        tree.postorder(tree.getRoot());
        System.out.println();
    }
}
