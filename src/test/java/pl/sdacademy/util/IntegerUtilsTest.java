package pl.sdacademy.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class IntegerUtilsTest {
    private IntegerUtils integerUtils;

    @BeforeEach
    void setUp() {
        integerUtils = new IntegerUtils();
    }

    @Test
    void shouldFilterNumber() {
        final int toFilter = 12345;
        final List<Integer> integerList = Arrays.asList(2, 4);
        final List<Integer> integerListFilteres = integerUtils.filter(toFilter, integerList);
        assertAll(
                () -> assertFalse(integerListFilteres.isEmpty()),
                () -> assertEquals(3, integerListFilteres.size()),
                () -> assertTrue(integerListFilteres.contains(1)),
                () -> assertTrue(integerListFilteres.contains(5)),
                () -> assertTrue(integerListFilteres.contains(3))

        );
    }

    @Test
    void shouldFilterDigitsGreaterThanWhenFilterApply() {
        final int toFilter = 12345;
        final int filter = 3;
        final List<Integer> filteredValues = integerUtils.filterDigitsGreaterThan(toFilter, filter);
        assertThat(filteredValues)
                .isNotEmpty()
                .hasSize(2)
                .contains(4, 5);
    }

    @Test
    void shouldReturnLastEvenDigitWhenEvenDigitExist() {
        final int toFilter = 12345;
        Optional<Integer> optionalInteger = integerUtils.getLastEvenDigit(toFilter);
        assertThat(optionalInteger)
                .isPresent()
                .hasValue(4);
    }

    @Test
    void shouldReturnNullWhenEvenDigitNotExist() {
        final int input = 13579;
        Optional<Integer> optionalInteger = integerUtils.getLastEvenDigit(input);
        assertThat(optionalInteger)
                .isNotPresent();
    }


    //Dopisz kolejne przypadki testowe, aby znaleźć bug w klasie którą testujesz.
    //Popraw znalezione bugi w implementacji.

}