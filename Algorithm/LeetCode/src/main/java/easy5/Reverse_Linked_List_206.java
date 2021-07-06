package easy5;

// TODO:
class Node2 {
    int val;
    Node2 next;

    public Node2() {
    }

    public Node2(int val) {
        this.val = val;
    }

    public Node2(int val, Node2 next) {
        this.val = val;
        this.next = next;
    }
}

public class Reverse_Linked_List_206 {

    public Node2 reverseList(Node2 head) {
        if (head == null) return null;

        Node2 prev = null;
        Node2 current = head;

        while (current != null) {
            Node2 temp = current.next;
            current.next = prev;
            prev = current;
            current = temp;
        }

        return head;
    }
}
