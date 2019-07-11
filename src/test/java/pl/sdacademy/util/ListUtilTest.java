package pl.sdacademy.util;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListUtilTest {

    @Test
    void shouldDoubleValues() {
        final List<Integer> valueToDuble = Arrays.asList(3, 5, 4, 1);

        List<Integer> doubleValues = ListUtil.doubleValues(valueToDuble);

        assertIterableEquals(Arrays.asList(6, 10, 8, 2), doubleValues);
    }


}