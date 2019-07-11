package pl.sdacademy.javagda25;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Month;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MonthsTest {

    @ParameterizedTest
    @MethodSource("getTestArgs")
    void shouldReturnTrueWhenProvaideMonthsThatHave31Days(final Month month) {
        final boolean has31days = Months.has31Days(month);
        assertThat(has31days).isTrue();
    }

    @ParameterizedTest
    @ArgumentsSource(ProvaideNegativeArguments.class)
    void shouldReturnFalseWhenProvaideLessThan31DaysMonths(final Month month) {
        final boolean has31days = Months.has31Days(month);
        assertThat(has31days).isFalse();
    }

    @ParameterizedTest
    @EnumSource(value = Month.class, names = {"FEBUARY", "APRIL", "JUNE", "SEPTEMBER", "NOVEMBER"})
    void shouldReturnFalseWhenProvaideLessThan31DaysMonthsFromEnum(final Month month) {
        final boolean has31days = Months.has31Days(month);
        assertThat(has31days).isFalse();
    }

    static Stream<Arguments> getTestArgs() {
        return Stream.of(
                Arguments.of(Month.JANUARY),
                Arguments.of(Month.MARCH),
                Arguments.of(Month.MAY),
                Arguments.of(Month.JULY),
                Arguments.of(Month.AUGUST),
                Arguments.of(Month.OCTOBER),
                Arguments.of(Month.DECEMBER)
        );

    }
}