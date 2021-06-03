import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;import static org.junit.jupiter.api.Assertions.*;

class PlayResultTest {

    @DisplayName("스트라이크 테스트")
    @ParameterizedTest
    @MethodSource("generateData")
    public void test1(List<Integer> playerLists, List<Integer> computerLists, int index) {
        PlayResult playResult = new PlayResult();
        int strikeCount = playResult.countStrike(playerLists, computerLists);

        int[] result = {1, 0};
        assertThat(strikeCount).isEqualTo(result[index]);
    }

    @DisplayName("볼 테스트")
    @ParameterizedTest
    @MethodSource("generateData")
    void test_ball(List<Integer> playerLists, List<Integer> computerLists, int index) {
        PlayResult playResult = new PlayResult();
        int ballCount = playResult.countBall(playerLists, computerLists);

        int[] result = {2, 0};
        assertThat(ballCount).isEqualTo(result[index]);
    }

    static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of(Arrays.asList(1, 2, 3), Arrays.asList(1, 3, 2), 0),
                Arguments.of(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6), 1)
        );
    }
}