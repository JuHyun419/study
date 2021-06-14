package study;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class SetTest {
    private Set<Integer> numbers;

    @BeforeEach
    void setUp() {
        numbers = new HashSet<>();
        numbers.add(1);
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
    }


    /**
     * 요구사항 1
     * Set의 size()메소드를 활용해 Set의 크기를 확인하는 학습 테스트 구현
     */
    @DisplayName("Set은 중복을 허용하지 않는다.")
    @Test
    void set_is_not_duplicate_elements() {
        assertThat(numbers.size()).isEqualTo(3);
    }


    /**
     * 요구사항 2
     * Set의 contains() 메소드를 활용해 1, 2, 3 의 값이 존재하는지를 확인하는 학습 테스트 구현
     * 중복 코드 제거 -> JUnit의 ParameterizedTest 활용
     */
    @DisplayName("여러 개의 파라미터를 한번에 테스트 한다")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void set_contains_test(int element) {
        assertThat(numbers.contains(element)).isTrue();
    }


    /**
     * 요구사항 3
     * 요구사항 2는 contains 메소드 결과 값이 true인 경우만 테스트가 가능
     * 입력값에 따라 결과값이 다른 경우에 대한 테스트도 가능하도록 구현
     * ex) 1, 2, 3 값은 contains 메소드 실행 결과 true이고 4,5 값은 false가 반환되는 테스트를 하나의 함수로 구현
     * @CsvSource 활용
     */
    @DisplayName("true, false 값을 같이 리턴한다")
    @ParameterizedTest
    @CsvSource(value = {"1:true", "2:true", "3:true", "4:false", "5:false"}, delimiter = ':')
    void set_contains_true_false(int element, boolean expected) {
        assertThat(numbers.contains(element)).isEqualTo(expected);
    }
}
