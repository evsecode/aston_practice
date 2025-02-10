package lesson_13.testng;

import lesson_13.FactorialCalculator;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.expectThrows;

public class FactorialCalculatorTest {
    @Test
    public void testFactorialOfOne() {
        assertEquals(FactorialCalculator.calculate(1), 1);
    }

    @Test
    public void testFactorialOfZero() {
        assertEquals(FactorialCalculator.calculate(0), 1);
    }

    @Test
    public void testFactorialOfPositiveNumber() {
        assertEquals(FactorialCalculator.calculate(5), 120);
    }

    @Test
    public void testFactorialOfLargeNumber() {
        assertEquals(FactorialCalculator.calculate(10), 3628800);
    }

    @Test
    public void testNegativeNumberThrowsException() {
        expectThrows(IllegalArgumentException.class, () -> FactorialCalculator.calculate(-1));
    }
}
