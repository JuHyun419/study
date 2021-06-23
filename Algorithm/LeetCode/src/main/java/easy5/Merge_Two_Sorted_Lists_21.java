package easy5;

// TODO:
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class Merge_Two_Sorted_Lists_21 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        /* 두 Node중 하나라도 null이면 다른 Node 리턴 */
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode head = new ListNode(0); // first head
        ListNode currentNode = head;

        while (l1 != null && l2 != null) {
            // 오름차순 -> 다음 노드가 작은 값을 가르키고
            // 해당 노드는 다음 노드를 가르키게 한다.
            if (l1.val < l2.val) {
                currentNode.next = l1;
                l1 = l1.next;
            } else {
                currentNode.next = l2;
                l2 = l2.next;
            }
            currentNode = currentNode.next;

            /* 한 Node가 null이면 그 뒤에 나머지 Node를 넣는다 */
            if (l1 == null) {
                currentNode.next = l2;
            }

            if (l2 == null) {
                currentNode.next = l1;
            }
        }
        return head.next;
    }
}
