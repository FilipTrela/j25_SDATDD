package pl.sdacademy.calculations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FibonacciSeriesTest {
    private final FibonacciSeries fibonacciSeries = new FibonacciSeries();

    @ParameterizedTest
    @ArgumentsSource(FibonacciPossitiveArguments.class)
    void shouldCalulateValueIndex(final int indeks, final long result) {
        final long actualResult = fibonacciSeries.compute(indeks);
        assertThat(actualResult).isEqualTo(result);
    }

    @ParameterizedTest
    @ArgumentsSource(FibonacciNegativeArguments.class)
    void shouldThrownException(final int indeks) {
        final Throwable throwable = assertThrows(IllegalArgumentException.class,
                () -> fibonacciSeries.compute(indeks));
        assertThat(throwable)
                .hasMessage("Index has to be positive")
                .extracting(Throwable::getCause)
                .isNull();
    }
}