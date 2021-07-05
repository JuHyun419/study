package easy5;

// TODO:
class Node {
    int val;
    Node next;

    public Node() {
    }

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, Node next) {
        this.val = val;
        this.next = next;
    }
}

public class Remove_Linked_List_Elements_203 {
    public Node removeElements(Node head, int val) {
        if (head == null) return null;

        Node current = head.next;
        Node prev = head;

        while (current != null) {
            if (current.val == val) {
                prev.next = current.next;
                current = prev.next;
            } else {
                current = current.next;
                prev = prev.next;
            }
        }
        if (head.val == val) {
            head = head.next;
        }
        return head;
    }

    public Node removeElements2(Node head, int val) {
        if (head == null) return null;

        head.next = removeElements2(head.next, val);
        return head.val == val ? head.next : head;
    }

    public Node removeElements3(Node head, int val) {
        if (head == null) return null;

        if (head.val == val) head = removeElements3(head.next, val);
        else head.next = removeElements3(head.next, val);

        return head;
    }

}
