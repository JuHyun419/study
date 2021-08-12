package string;

import java.util.ArrayList;
import java.util.List;

// TODO
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}


public class Binary_Tree_Paths_257 {

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> path = new ArrayList<>();
        preOrder(root, path, "");
        return path;
    }

    public void preOrder(TreeNode root, List<String> path, String str) {
        if (root == null) return;

        if (root.left == null && root.right == null) {
            path.add(str + root.val);
            return;
        }
        preOrder(root.left, path, str + root.val + "->");
        preOrder(root.right, path, str + root.val + "->");
    }
}
