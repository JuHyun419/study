package linkedlist;

/**
 * 노드
 * - 각각의 노드는 "데이터 필드"와 하나 혹은 그 이상의 "링크 필드"로 구성
 * - 링크 필드는 다음 노드를 참조
 * - 첫 번째 노드의 주소는 따로 저장해야 함(head)
 */
public class Node<T> {

    public T data;
    public Node<T> next;

    public Node(T data) {
        this.data = data;
        next = null;
    }

}
