package pl.sdacademy.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PolishPersonUtilTest2 {
    private PolishPersonUtil polishPersonUtil;

    @BeforeEach
    void setUp() {
        polishPersonUtil = new PolishPersonUtil();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "Ula, Kaczyńska",
            "Marta, Wolska",
            "Kasia, Lewandowska"})
    void shouldReturnTrueWhenParametrIsPolishWomenName(final String name, final String surname) {
        final boolean actualValue = polishPersonUtil.isWomanWithTypicalPolishSurname(name, surname);
        assertThat(actualValue).isTrue();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/source.csv")
    void shouldReturnFalseWhenParametrIsMenName(final String name, final String surname) {
        final boolean actualValue = polishPersonUtil.isWomanWithTypicalPolishSurname(name, surname);
        assertThat(actualValue).isFalse();
    }
}