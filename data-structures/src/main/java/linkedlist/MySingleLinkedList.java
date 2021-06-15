package linkedlist;

import java.util.Objects;

public class MySingleLinkedList<T> {

    public Node<T> head; // 첫 번째 노드의 주소
    public int size;

    public MySingleLinkedList() {
        head = null;
        size = 0;
    }


    /**
     * insert item first place
     * (1) 새로운 노드의 next 필드가 현재의 head 노드를 가리키도록 한다.
     * (2) 새로운 노드를 새로운 head 노드로 한다.
     * (3) 사이즈 + 1
     */
    public void addFirst(T item) {
        Node<T> newNode = new Node<>(item);
        newNode.next = head; // (1)
        head = newNode;      // (2)
        size++; // (3)
    }


    /**
     * insert item after
     * (1) 새로운 노드의 next 필드가 before의 다음 노드를 가리키도록 한다.
     * (2) 새로운 노드를 before의 다음 노드로 만든다.
     * (3) 사이즈 + 1
     */
    public void addAfter(Node<T> before, T item) {
        Node<T> temp = new Node<>(item);
        temp.next = before.next; // (1)
        before.next = temp; // (2)
        size++; // (3)
    }


    /**
     * insert item index
     */
    public void add(int index, T item) {
        checkIndex(index, size);
        if (index == 0) {
            addFirst(item);
        } else {
            Node<T> node = getNode(index - 1);
            addAfter(node, item);
        }
    }


    /**
     * remove index node
     */
    public T remove(int index) {
        checkIndex(index, size);
        if (index == 0) {
            return removeFirst();
        }
        Node<T> prev = getNode(index - 1);
        return removeAfter(prev);
    }


    /**
     * remove node that has item
     * beforeNode 는 항상 node의 직전 노드를 가리킴
     * node가 첫번째 노드일 경우 -> beforeNode는 null
     */
    public T remove(T item) {
        Node<T> node = head;
        Node<T> beforeNode = null;

        while (!isNull(node) && !node.data.equals(item)) {
            beforeNode = node;
            node = node.next;
        }
        if (isNull(node)) {
            return null;
        }
        if (isNull(beforeNode)) {
            return removeFirst();
        }
        return removeAfter(beforeNode);
    }


    /**
     * remove first item
     * (1) head가 null이 아니라면, head가 현재 head노드의 다음 노드를 가리키게 만든다.
     */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T data = head.data;
        head = head.next;
        return data;
    }


    /**
     * remove node after
     * (1) before의 다음 노드가 null이 아니라면 before의 next필드가 현재 next노드의 다음 노드를 가리키게 만든다.
     */
    public T removeAfter(Node<T> before) {
        if (isNull(before)) {
            return null;
        }
        T data = before.next.data;
        before.next = before.next.next;
        return data;
    }


    /**
     * get
     *
     * @return
     */
    public T get(int index) {
        checkIndex(index, size);
        return getNode(index).data;
    }


    public Node<T> getNode(int index) {
        checkIndex(index, size);
        Node<T> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }


    /**
     * search
     *
     * @return index of item if item exists, else return -1
     */
    public int indexOf(T item) {
        Objects.requireNonNull(item);
        Node<T> node = head;
        int index = 0;
        while (!isNull(node)) {
            if (node.data.equals(item)) {
                return index;
            }
            node = node.next;
            index++;
        }
        return -1;
    }


    /**
     * @return if this.size = 0 return true, else return false
     */
    public boolean isEmpty() {
        return size == 0;
    }


    public boolean isNull(Node<T> node) {
        return Objects.isNull(node);
    }

    private void checkIndex(int index, int size) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Range check failed, Index: " + index + ", size: " + size);
        }
    }


    public static void main(String[] args) {
        MySingleLinkedList<String> linkedList = new MySingleLinkedList<>();
        linkedList.add(0, "Saturday");
        linkedList.add(1, "Friday");
        linkedList.add(0, "Monday");  // M, S, F
        linkedList.add(2, "Tuesday"); // M, S, T, F

        String str = linkedList.get(2); // Tuesday
        System.out.println("str = " + str);
        linkedList.remove(2); // M, S, F

        int position = linkedList.indexOf("Friday"); // 2
        System.out.println("position = " + position);
    }
}

