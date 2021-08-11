package step2.baseball.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class NumberGeneratorTest {

    @Order(1)
    @RepeatedTest(10)
    @DisplayName("1 ~ 9 까지 세 자리 수를 리턴한다")
    void should_return_three_numbers() {
        // given & when
        final List<Integer> numbers = NumberGenerator.of();

        // then
        assertThat(numbers.size()).isEqualTo(3);
        assertThat(numbers.stream().allMatch(i -> i >= 1 && i <= 9)).isTrue();
    }

    @Order(2)
    @Test
    @DisplayName("3개의 수는 모두 다른 수여야 한다.")
    void should_different_each_numbers() {
        // given
        final List<Integer> numbers = NumberGenerator.of();

        // when
        int num1 = numbers.get(0);
        int num2 = numbers.get(1);
        int num3 = numbers.get(2);

        // then
        assertThat(num1).isNotEqualTo(num2);
        assertThat(num1).isNotEqualTo(num3);
        assertThat(num2).isNotEqualTo(num3);
    }
}