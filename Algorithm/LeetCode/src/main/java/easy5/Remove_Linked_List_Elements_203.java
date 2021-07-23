package easy5;

// TODO:

public class Remove_Linked_List_Elements_203 {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;

        ListNode current = head.next;
        ListNode prev = head;

        while (current != null) {
            if (current.val == val) {
                prev.next = current.next;
                current = current.next;
                //current = prev.next;
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

    public ListNode removeElements2(ListNode head, int val) {
        if (head == null) return null;

        head.next = removeElements2(head.next, val);
        return head.val == val ? head.next : head;
    }

    public ListNode removeElements3(ListNode head, int val) {
        if (head == null) return null;

        if (head.val == val) head = removeElements3(head.next, val);
        else head.next = removeElements3(head.next, val);

        return head;
    }

}
