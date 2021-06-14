import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorExampleTest {
    CalculatorExample calc;

    @BeforeEach
    void setUp() {
        calc = new CalculatorExample();
    }

    @Test
    void 덧셈() {
        assertEquals(7, calc.add(3, 4));
    }

    @Test
    void 뺄셈() {
        assertEquals(1, calc.subtract(5, 4));
    }

    @Test
    void 곱셈() {
        assertEquals(6, calc.multiply(2, 3));
    }

    @Test
    void 나눗셈() {
        assertEquals(2, calc.divide(8, 4));
    }

    @ParameterizedTest
    @CsvSource({"1,2,3", "3,4,7", "10,20,30"})
    void 여러개의_덧셈_테스트(int num1, int num2, int expect) {
        int actual = calc.add(num1, num2);
        assertEquals(expect, actual);
    }

    @AfterEach
    void tearDown() {
        calc = null;
    }
}