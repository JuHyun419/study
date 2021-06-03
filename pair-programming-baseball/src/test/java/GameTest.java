import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @RepeatedTest(10)
    @DisplayName("1부터 9까지 랜덤하게 3개의 수를 생성한다")
    @Test
    public void test() {
        Game game = new Game();
        List<Integer> list = game.generateRandomNumber();

        assertEquals(3, list.size());
        assertTrue(list.stream().allMatch(i -> i >= 1 && i <= 9));
    }
}