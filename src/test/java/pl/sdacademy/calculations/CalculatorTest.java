package pl.sdacademy.calculations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void shouldAddTwoNumber() {
        //given
        final Calculator calculator = new Calculator();
        final double numA = 2;
        final double numB = 3;
        //when
        final double actualResult = calculator.add(numA, numB);
        //then
        assertEquals(5, actualResult);
    }

    @Test
    void shouldSubstractTwoNumbers() {
        final Calculator calculator = new Calculator();
        final double numA = 3.8;
        final double numB = 2.5;

        final double actualResult = calculator.subtract(numB, numA);

        assertEquals(-1.3, actualResult, 0.1);
    }

    @Test
    void shouldMultiplyTwoNumbers() {
        final Calculator calculator = new Calculator();
        final double numA = 2.1;
        final double numB = 3.0;

        final double actualResult = calculator.multiply(numA, numB);

        assertEquals(6.3, actualResult, 0.1);
    }

}