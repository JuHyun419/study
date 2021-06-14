package study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringTest {
    @Test
    void replace() {
        final String actual = "abc".replace("b", "d");
        assertThat(actual).isEqualTo("adc");
    }

    /**
     * 요구사항 1
     * "1,2"을 ,로 split했을 때 1과 2로 잘 분리되는지 확인하는 학습 테스트 구현
     * "1"을 ,로 split했을 때 1만을 포함하는 배열이 반환되는지에 대한 학습테스트 구현
     */
    @DisplayName("1,2를 ,로 분리하면 1과 2로 분리가 된다")
    @Test
    void split_two_string() {
        // given
        final String str = "1,2";

        // when
        final String[] splits = str.split(",");

        // then
        assertThat(splits[0]).isEqualTo("1");
        assertThat(splits[1]).isEqualTo("2");

        assertThat(splits).isNotNull()
                .isNotEmpty()
                .contains("1");

        assertThat(splits).containsExactly("1", "2");
    }

    @DisplayName("1을 ,로 분리하면 1만 리턴한다")
    @Test
    void split_one_string() {
        // given
        final String str = "1";

        // when
        final String[] split = str.split(",");

        // then
        assertThat(split.length).isEqualTo(1);
        assertThat(split).containsExactly("1");
    }


    /**
     * 요구사항 2
     * "(1,2)" 값이 주어졌을 때 String의 substring() 메소드를 활용해 ()을 제거하고 "1,2"를 반환하도록 구현한다.
     */
    @DisplayName("문자열에서 괄호를 제거하고 split 한다")
    @Test
    void remove_bracket_split() {
        // given
        String str = "(1,2)";
        final String regex = "^[(]|[)]$";

        // when
        str = str.substring(1, str.length() - 1); // use substring
        str = str.replaceAll(regex, ""); // use regex
        final String[] splits = str.split(",");

        // then
        assertThat(splits[0]).isEqualTo("1");
        assertThat(splits[1]).isEqualTo("2");
        assertThat(splits).isNotNull()
                .isNotEmpty()
                .contains("1");
        assertThat(splits).containsExactly("1", "2");
    }


    /**
     * 요구사항 3
     * 1) "abc" 값이 주어졌을 때 String의 charAt() 메소드를 활용해 특정 위치의 문자를 가져오는 학습 테스트 구현
     * 2) charAt()의 위치 값을 벗어나면 IndexOutOfBoundsException이 발생하는 부분에 대한 학습 테스트 구현
     * 1)2)JUnit의 @DisplayName을 활용해 테스트 메소드의 의도를 드러낸다.
     */

    @DisplayName("String의 charAt 메소드는 특정 위치의 문자를 가져온다.")
    @Test
    void charAt_test() {
        // given
        final String str = "PlayGround";

        // when
        char ch1 = str.charAt(0);
        char ch2 = str.charAt(4);

        // then
        assertThat(ch1).isEqualTo('P');
        assertThat(ch2).isEqualTo('G');
    }

    @DisplayName("String의 charAt 메소드는 특정 위치를 벗어나면 예외를 발생한다")
    @Test
    void charAt_outOfIndex_test() {
        assertThatThrownBy(() -> {
            String str = "abcd";
            char ch = str.charAt(10); })
                .isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessageContaining("range: 10");
    }

}
