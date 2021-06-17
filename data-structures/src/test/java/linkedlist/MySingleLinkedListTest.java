package linkedlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MySingleLinkedListTest {

    private MySingleLinkedList<String> linkedList;

    @BeforeEach
    void setUp() {
        linkedList = new MySingleLinkedList<>();
        linkedList.add(0, "A");
        linkedList.add(1, "B");
    }

    @Test
    @DisplayName("addFirst 메소드는 첫 번째 index에 데이터를 삽입한다.")
    void addFirst() {
        /* given */
        linkedList.addFirst("First");

        /* when */
        String data = linkedList.get(0);

        /* then */
        assertThat(data).isEqualTo("First");
    }

    @Test
    @DisplayName("addAfter 메소드는 특정 Node 다음에 삽입한다.")
    void addAfter() {
        /* given */
        Node<String> prev = linkedList.getNode(1);

        /* when */
        linkedList.addAfter(prev, "C");

        /* then */
        assertThat(linkedList.get(1)).isEqualTo("B");
        assertThat(linkedList.get(2)).isEqualTo("C");
    }

    @Test
    @DisplayName("add 메소드는 지정된 위치에 데이터를 삽입한다.")
    void add() {
        /* given & when */

        /* then */
        assertThat(linkedList.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("add 메소드의 index는 양수여야 한다.")
    void add_method_should_positive_number() {
        /* given */
        int index = -1;

        /* when & then */
        assertThatThrownBy(() -> linkedList.add(index, "Juhyun"))
                .isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessageContaining("Range check");
    }

    @Test
    @DisplayName("add 메소드의 index는 List의 size보다 작아야 한다.")
    void add_index_should_lower_than_size() {
        /* given */
        linkedList.add(0, "Test");
        linkedList.add(0, "Test");
        linkedList.add(0, "Test");
        linkedList.add(0, "Test");

        /* when */
        int index = 10;

        /* then */
        assertThatThrownBy(() -> linkedList.add(index, "JH"))
                .isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessageContaining("Range check");
    }

    @Test
    @DisplayName("List의 특정 index에 해당하는 Node를 삭제한다.")
    void remove_index() {
        /* given */

        /* when */
        linkedList.remove(0);

        /* then */
        assertThat(linkedList.size()).isEqualTo(1);
        assertThat(linkedList.get(0)).isEqualTo("B");
    }

    @Test
    @DisplayName("List의 특정 item에 해당하는 Node를 삭제한다.")
    void remove_item() {
        /* given */

        /* when */
        linkedList.remove("A");

        /* then */
        assertThat(linkedList.size()).isEqualTo(1);
        assertThat(linkedList.get(0)).isEqualTo("B");
    }

    @Test
    @DisplayName("List의 첫 번째 Node를 삭제한다.")
    void removeFirst() {
        /* given */

        /* when */
        linkedList.removeFirst();

        /* then */
        assertThat(linkedList.size()).isEqualTo(1);
        assertThat(linkedList.get(0)).isEqualTo("B");
    }

    @Test
    @DisplayName("index에 해당하는 Node의 data를 리턴한다")
    void get() {
        /* given */

        /* when */
        String data1 = linkedList.get(0);
        String data2 = linkedList.get(1);

        /* then */
        assertThat(data1).isEqualTo("A");
        assertThat(data2).isEqualTo("B");
    }

    @Test
    @DisplayName("동일한 item을 가진 2개의 Node 객체는 동일하다.")
    void getNode() {
        /* given */

        /* when */
        Node<String> node = new Node<>("A");
        Node<String> newNode = linkedList.getNode(0);

        /* then */
        assertThat(node).isEqualTo(newNode);
        assertThat(newNode.data).isEqualTo("A");
    }


}