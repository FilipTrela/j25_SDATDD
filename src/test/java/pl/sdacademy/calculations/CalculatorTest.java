package pl.sdacademy.calculations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import pl.sdacademy.exceptions.DivisionByZeroException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    void shouldAddTwoNumber() {
        //given
        final double numA = 2;
        final double numB = 3;
        //when
        final double actualResult = calculator.add(numA, numB);
        //then
        assertEquals(5, actualResult);
    }

    @Test
    void shouldSubstractTwoNumbers() {
        final double numA = 3.8;
        final double numB = 2.5;

        final double actualResult = calculator.subtract(numB, numA);

        assertEquals(-1.3, actualResult, 0.1);
    }

    @Test
    void shouldMultiplyTwoNumbers() {
        final double numA = 2.1;
        final double numB = 3.0;

        final double actualResult = calculator.multiply(numA, numB);

        assertEquals(6.3, actualResult, 0.1);
    }

    @Test
    void shoulThrowSomeExceptionWhenTryaingDivideByZero() {
        final double numA = 0;
        final double numB = 4;
        try {
            calculator.divide(numB, numA);
            fail("Exception not thrown");
        } catch (final DivisionByZeroException dbze) {
            assertThat(dbze)
                    .hasNoCause()//nie ma wyjątku który spowodował ten wyjątek.
                    .hasMessage("Cannot divide by 0!");
        }
    }

    @ParameterizedTest
    @ArgumentsSource(DividePossitiveArgument.class)
    void shouldDivideTwoNumbers(final double numberA, final double numberB, final double result) {

        final double actualResult = calculator.divide(numberA, numberB);

        assertEquals(result, actualResult, 0.1);
    }

}