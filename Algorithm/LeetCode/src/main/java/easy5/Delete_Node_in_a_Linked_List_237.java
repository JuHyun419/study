package easy5;

class Node3 {
    int val;
    Node3 next;

    public Node3(int val) {
        this.val = val;
    }
}

public class Delete_Node_in_a_Linked_List_237 {
    public void deleteNode(ListNode node) {
        if (node == null) return;

        node.val = node.next.val;
        node.next = node.next.next;
    }
}
