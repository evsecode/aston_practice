package lesson_13.junit5;

import lesson_13.FactorialCalculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FactorialCalculatorTest {
    private final FactorialCalculator calculator = new FactorialCalculator();

    @Test
    void testFactorialOfZero() {
        assertEquals(1, calculator.calculate(0));
    }

    @Test
    void testFactorialOfOne() {
        assertEquals(1, calculator.calculate(1));
    }

    @Test
    void testFactorialOfPositiveNumber() {
        assertEquals(120, calculator.calculate(5));
    }

    @Test
    void testFactorialOfLargeNumber() {
        assertEquals(3628800, calculator.calculate(10));
    }

    @Test
    void testNegativeNumberThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate(-1));
    }
}
