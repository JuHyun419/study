package linkedlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MySingleLinkedListTest {

    private MySingleLinkedList<String> linkedList;

    @BeforeEach
    void setUp() {
        linkedList = new MySingleLinkedList<>();
    }

    @Test
    @DisplayName("add 메소드는 지정된 위치에 데이터를 삽입한다.")
    void add_Test() {
        /* given & when */
        linkedList.add(0, "A");
        linkedList.add(1, "B");
        linkedList.add(2, "C");

        /* then */
        assertThat(linkedList.size).isEqualTo(3);
    }

    @Test
    @DisplayName("addFirst 메소드는 첫 번째 index에 데이터를 삽입한다.")
    void addFirst_Test() {
        /* given */
        linkedList.add(0, "B");
        linkedList.add(1, "C");
        linkedList.addFirst("A");

        /* when */
        String data = linkedList.get(0);

        /* then */
        assertThat(data).isEqualTo("A");
    }
}