package step1.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GameTest {

    StringCalculator stringCalculator;

    @BeforeEach
    void setup() {
        stringCalculator = new StringCalculator();
    }

    @DisplayName("문자열 형식의 계산기")
    @Test
    void calculator() {
        // given
        final String value = "2 + 3 * 4 / 2";

        // when
        final double result = stringCalculator.calcResult(value);

        // then
        assertThat(result).isEqualTo(10.0);
    }

    @DisplayName("세 개 이하의 문자는 예외를 반환한다")
    @Test
    void thrown_less_then_three_string() {
        assertThatThrownBy(() -> {
            final String value = "2 + ";
            final double result = stringCalculator.calcResult(value); })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("최소 3개");
    }
}