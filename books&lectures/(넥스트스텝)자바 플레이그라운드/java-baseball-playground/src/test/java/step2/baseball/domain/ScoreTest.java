package step2.baseball.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTest {

    @DisplayName("볼 테스트")
    @ParameterizedTest
    @MethodSource("generateBallData")
    void ball(List<Integer> computers, String player, int expected) {
        // given & when
        int actual = Score.ballScore(computers, player);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("스트라이크 테스트")
    @ParameterizedTest
    @MethodSource("generateStrikeData")
    void strike(List<Integer> computers, String player, int expected) {
        // given & when
        int actual = Score.strikeScore(computers, player);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> generateBallData() {
        return Stream.of(
                Arguments.of(Arrays.asList(1, 2, 3), "456", 0),
                Arguments.of(Arrays.asList(1, 2, 3), "231", 3),
                Arguments.of(Arrays.asList(3, 6, 4), "256", 1),
                Arguments.of(Arrays.asList(4, 8, 5), "874", 2)
        );
    }

    static Stream<Arguments> generateStrikeData() {
        return Stream.of(
                Arguments.of(Arrays.asList(1, 2, 3), "456", 0),
                Arguments.of(Arrays.asList(1, 2, 3), "146", 1),
                Arguments.of(Arrays.asList(3, 6, 4), "368", 2),
                Arguments.of(Arrays.asList(4, 8, 5), "485", 3)
        );
    }
}
