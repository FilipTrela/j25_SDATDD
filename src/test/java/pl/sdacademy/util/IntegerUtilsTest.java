package pl.sdacademy.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

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

}