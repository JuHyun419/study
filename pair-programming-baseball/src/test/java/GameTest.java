import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

class GameTest {

    @RepeatedTest(10)
    @DisplayName("1부터 9까지 랜덤하게 3개의 수를 생성한다")
    public void test() {
        Game game = new Game();
        List<Integer> list = game.generateRandomNumber();

        assertThat(3).isEqualTo(list.size());
        assertThat(list.stream().allMatch(i -> i >= 1 && i <= 9)).isTrue();
    }
}